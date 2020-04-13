package com.potato112.springdemo.conf;



/*import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;

import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;*/

/**
 * Redis is in-memory data structure server, that may be used to store http sessions.
 * This is embedded Redis server configuration for session services unit tests.
 * To use Redis the Jedis client is required to use.
 */
//@TestConfiguration FIXME REDIS
public class TestRedisConfiguration  {

   /* private RedisServer redisServer;

    public TestRedisConfiguration(RedisProperties redisProperties) throws IOException {
        this.redisServer = new RedisServer(redisProperties.getPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
*/
}
