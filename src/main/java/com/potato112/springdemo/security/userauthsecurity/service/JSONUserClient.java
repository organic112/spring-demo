package com.potato112.springdemo.security.userauthsecurity.service;


import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.OffsetResponseVo;
import com.potato112.springdemo.web.ui.UserOverviewResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "juserclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONUserClient {

    @GetMapping(value = "/api/v1/user/login/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDetailsAuthority getUserByName(@PathVariable("username") String userName);

    @PostMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody UserVo userVo);

    @GetMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
    OffsetResponseVo<UserOverviewResponseVo> getUsers(@SpringQueryMap Map<String, String> params);

}
