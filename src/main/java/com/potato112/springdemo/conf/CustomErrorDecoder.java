package com.potato112.springdemo.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato112.springdemo.common.ErrorResult;
import com.potato112.springdemo.web.ui.common.exceptions.SysValidationException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;


import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 400) {
            ErrorResult responseData;
            ObjectMapper mapper = new ObjectMapper();
            try {
                Response.Body body = response.body();
                InputStream src = body.asInputStream();
                responseData = mapper.readValue(src, ErrorResult.class);
            } catch (Exception e) {
                return FeignException.errorStatus(methodKey, response);
            }
            return new SysValidationException(responseData);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
