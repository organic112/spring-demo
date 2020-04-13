package com.potato112.springdemo.conf;

import com.potato112.springdemo.common.ErrorResult;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {


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
            //FIXME dedicated exception
            return new RuntimeException(responseData.getMainMessage());
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
