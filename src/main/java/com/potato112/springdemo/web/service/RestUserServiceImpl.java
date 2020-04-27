package com.potato112.springdemo.web.service;

import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.user.UserService;
import com.potato112.springdemo.web.service.user.UserVo;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersVo;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class RestUserServiceImpl implements UserService {

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
    public String create(UserVo userVo) {

        log.info("try insert user with id:" + userVo.getId());

        return this.jsonUserClient.create(userVo);
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
    public UserFormParametersVo getUserFromParameters() {

        System.out.println("ECHO XXXX Additional PARAMETERS !");
        return this.jsonUserClient.getUserParameters();
    }
}
