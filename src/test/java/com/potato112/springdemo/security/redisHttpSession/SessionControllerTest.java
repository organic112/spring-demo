package com.potato112.springdemo.security.redisHttpSession;

import com.potato112.springdemo.conf.TestRedisConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * note Redis server for tests is configured in TestRedisConfiguration
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SessionControllerTest {

    @LocalServerPort
    private int port;
    private Jedis jedis;
    private TestRestTemplate testRestTemplateNoAuth;
    private TestRestTemplate testRestTemplateAuthenticated;

    /**
     * 1. Create Rest client with template, to start Http session on rest controller
     * - no auth
     * - with auth (username and pass for basic auth - valid credentials configured in SecurityConfig)
     * 2. Create Jedis client to use Redis session storage.
     */
    @Before
    public void init() {
        testRestTemplateNoAuth = new TestRestTemplate();
        testRestTemplateAuthenticated = new TestRestTemplate("admin", "test_pass_01", null);
        jedis = new Jedis("localhost", 6379);
    }

    @After
    public void close() {
        jedis.flushAll();
    }

    @Test
    public void shouldRedisStoreNoSessionWhenNoRequestSendYet() {
        Set<String> result = jedis.keys("*");
        assertEquals(0, result.size());
    }

    @Test
    public void shouldNotBeAuthenticatedWhenNoValidCredentials() {
        ResponseEntity<String> result = testRestTemplateNoAuth.getForEntity(getTestUrl(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void shouldSessionBeControlledByRedis() {

        // #1 just successfully authenticated request
        ResponseEntity<String> result = testRestTemplateAuthenticated.getForEntity(getTestUrl(), String.class);
        assertEquals("hello admin", result.getBody()); // login worked

        Set<String> redisResult = jedis.keys("*");
        printRedisSession(redisResult);
        assertTrue(redisResult.size() > 0); // redis is populated with session data

        // #2 get cookie from authenticated session and put into template with no auth
        // (now template with no auth should be authenticated).
        String sessionCookie = result.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);
        HttpEntity<String> httpEntityWithCookie = new HttpEntity<>(headers);

        // check if authenticated by cookie - YES
        result = testRestTemplateNoAuth.exchange(getTestUrl(), HttpMethod.GET, httpEntityWithCookie, String.class);
        assertEquals("hello admin", result.getBody()); // YES - access with session worked

        // clear all keys (session data) in Redis server
        jedis.flushAll();

        // check if authenticated by cookie - NO cookie not recognized in Redis any more
        result = testRestTemplateNoAuth.exchange(getTestUrl(), HttpMethod.GET, httpEntityWithCookie, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode()); // access denied because sessions are removed from Redis
    }

    private void printRedisSession(Set<String> redisResult) {
        System.out.println("Redis Keys:");
        redisResult.stream().forEach(key -> System.out.println(key));
    }

    private String getTestUrl() {
        return "http://localhost:" + port;
    }
}