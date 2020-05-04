package com.potato112.springdemo.web.service.user;

import com.potato112.springdemo.web.service.search.model.OffsetResponseDto;
import com.potato112.springdemo.web.service.search.model.OffsetSearchDto;
import com.potato112.springdemo.web.service.rest.JSONUserClient;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.user.model.UsersService;
import com.potato112.springdemo.web.service.user.model.UserDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersDto;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceRestImpl implements UsersService {

    private final JSONUserClient jsonUserClient;
    private final WebSecurityService securityService;

    public UserServiceRestImpl(JSONUserClient jsonUserClient, WebSecurityService securityService) {
        this.jsonUserClient = jsonUserClient;
        this.securityService = securityService;
    }

    @Override
    public UserDetailsAuthority getUserByName(String userName) {
        return this.jsonUserClient.getUserByName(userName);
    }

    @Override
    public Optional<UserDto> getUser(String id) {
        return Optional.of(this.jsonUserClient.getUserById(id));
    }

    @Override
    public Collection<UserOverviewResponseDto> getUsers(OffsetSearchDto searchVo) {
        OffsetResponseDto<UserOverviewResponseDto> response = getForSearch(searchVo);
        return response.getContent();
    }

    @Override
    public int count(OffsetSearchDto searchVo) {
        OffsetResponseDto<UserOverviewResponseDto> response = getForSearch(searchVo);
        return (int) response.getOffsetInfo().getTotal();
    }

    @Override
    public String create(UserDto userDto) {
        log.info("try insert user with id:" + userDto.getId());
        return this.jsonUserClient.create(userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        System.out.println("try update user" + userDto.getId());
        return this.jsonUserClient.update(userDto);
    }

    @Override
    public UserFormParametersDto getUserFromParameters() {
        System.out.println("ECHO XXXX Additional PARAMETERS !");
        return this.jsonUserClient.getUserParameters();
    }

    private OffsetResponseDto<UserOverviewResponseDto> getForSearch(OffsetSearchDto searchVo) {
        return this.jsonUserClient.getUsers(searchVo.toParamMap());
    }
}
