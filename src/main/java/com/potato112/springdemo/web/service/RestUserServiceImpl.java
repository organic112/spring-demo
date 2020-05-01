package com.potato112.springdemo.web.service;

import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.user.UsersService;
import com.potato112.springdemo.web.service.user.UserDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersDto;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class RestUserServiceImpl implements UsersService {

    private final JSONUserClient jsonUserClient;
    private final WebSecurityService securityService;

    public RestUserServiceImpl(JSONUserClient jsonUserClient, WebSecurityService securityService) {
        this.jsonUserClient = jsonUserClient;
        this.securityService = securityService;
    }

    @Override
    public UserDetailsAuthority getUserByName(String userName) {

        return this.jsonUserClient.getUserByName(userName);
    }

    @Override
    public String create(UserDto userDto) {

        log.info("try insert user with id:" + userDto.getId());

        return this.jsonUserClient.create(userDto);
    }

    @Override
    public Collection<UserOverviewResponseDto> getUsers(OffsetSearchDto searchVo) {

        OffsetResponseDto<UserOverviewResponseDto> response = getForSearch(searchVo);
        return response.getContent();
    }

    private OffsetResponseDto<UserOverviewResponseDto> getForSearch(OffsetSearchDto searchVo) {
        return this.jsonUserClient.getUsers(searchVo.toParamMap());
    }

    @Override
    public int count(OffsetSearchDto searchVo) {
        OffsetResponseDto<UserOverviewResponseDto> response = getForSearch(searchVo);
        return (int) response.getOffsetInfo().getTotal();
    }

    @Override
    public UserFormParametersDto getUserFromParameters() {

        System.out.println("ECHO XXXX Additional PARAMETERS !");
        return this.jsonUserClient.getUserParameters();
    }

    @Override
    public Optional<UserDto> getUsers(String id) {

        return Optional.of(this.jsonUserClient.getUserById(id));
    }

    @Override
    public UserDto update(UserDto userDto) {

        return this.jsonUserClient.update(userDto);
    }
}
