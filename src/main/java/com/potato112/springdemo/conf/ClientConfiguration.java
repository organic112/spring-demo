package com.potato112.springdemo.conf;

import com.potato112.springdemo.web.service.user.model.UserDetailsDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.entity.ContentType;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class ClientConfiguration {

    private static final String USER_FOO_CONTEXT_HEADER = "UserFooContext";

    @Value("${spring.security.user.name}")
    private String techUser;

    @Value("${spring.security.user.password}")
    private String techPassword;

    private final WebSecurityService webSecurityService;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.DEBUG;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    /**
     * Request interceptor for REST client queries
     */
    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            log.info("Echo01 request interceptor (request headers setup)");

            String auth = techUser + ":" + techPassword;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);

            requestTemplate.header(HttpHeaders.AUTHORIZATION, authHeader);
            requestTemplate.header(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            // add user organization header for requests not related to "/login/"
            if (!requestTemplate.url().contains("/login/")) {

                UserDetailsAuthority user = webSecurityService.getUser();
                UserDetailsDto userDetailsDto = user.getUserDetailsDto();

                // FIXME ! check this if this affects routing (foo = group)
                String userFooId = userDetailsDto.getSelectedOrganizationId();
                requestTemplate.header(USER_FOO_CONTEXT_HEADER, userFooId);

                log.info("Echo02 request interceptor (request headers setup)");
            }
        };
    }
}
