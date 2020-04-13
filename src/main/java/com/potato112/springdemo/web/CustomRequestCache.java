package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRequestCache extends HttpSessionRequestCache {

    /**
     * don't save internal requests form framework
     */
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {

        logger.info("request path info:" + request.getPathInfo());
        logger.info("request session id:" + request.getSession().getId());
        logger.info("request header names:" + request.getHeaderNames().toString());

        logger.info("response status:" + response.getStatus());
        logger.info("response buffer size:" + response.getBufferSize());


        if (!WebSecurityService.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}
