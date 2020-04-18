package com.potato112.springdemo.security.userauthsecurity.service;


import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "juserclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONUserClient {

    @GetMapping(value = "/api/v1/user/login/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDetailsAuthority getUserByName(@PathVariable("username") String userName);



    //TODO


}
