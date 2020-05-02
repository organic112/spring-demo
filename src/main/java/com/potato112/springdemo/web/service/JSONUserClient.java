package com.potato112.springdemo.web.service;


import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.web.service.user.UserDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersDto;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "juserclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONUserClient {

    @GetMapping(value = "/api/v1/user/login/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDetailsAuthority getUserByName(@PathVariable("username") String userName);

    @PostMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody UserDto userDto);

    @GetMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
    OffsetResponseDto<UserOverviewResponseDto> getUsers(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/api/v1/user/create-parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    UserFormParametersDto getUserParameters();

    @GetMapping(value = "/api/v1/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getUserById(@PathVariable("userId") String userId);

    @PutMapping(value = "/api/v1/user", produces =  MediaType.APPLICATION_JSON_VALUE)
    UserDto update(@RequestBody UserDto userDto);


}
