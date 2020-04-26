package com.potato112.springdemo.security.userauthsecurity.service;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.OffsetResponseVo;
import com.potato112.springdemo.web.ui.UserFormParametersVo;
import com.potato112.springdemo.web.ui.UserOverviewResponseVo;
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
    public Collection<UserOverviewResponseVo> getUsers(OffsetSearchVo searchVo) {

        OffsetResponseVo<UserOverviewResponseVo> response = getForSearch(searchVo);
        return response.getContent();
    }

    private OffsetResponseVo<UserOverviewResponseVo> getForSearch(OffsetSearchVo searchVo) {
        return this.jsonUserClient.getUsers(searchVo.toParamMap());
    }

    @Override
    public int count(OffsetSearchVo searchVo) {
        OffsetResponseVo<UserOverviewResponseVo> response = getForSearch(searchVo);
        return (int) response.getOffsetInfo().getTotal();
    }

    @Override
    public UserFormParametersVo getUserFromParameters() {

        System.out.println("ECHO XXXX Additional PARAMETERS !");
        return this.jsonUserClient.getUserParameters();
    }
}
