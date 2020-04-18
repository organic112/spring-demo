package com.potato112.springdemo.security.userauthsecurity.service;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.UserOverviewResponseVo;

import java.util.Collection;

public interface UserService {

    // TODO service other methods
   /* String create(UserVO userVO);

    UserVO update(UserVO userVO);

    Optional<UserVo> getUser(String id);

    ......
    */

    String create(UserVo userVo);

    UserDetailsAuthority getUserByName(String userName);

    Collection<UserOverviewResponseVo> getUsers(OffsetSearchVo searchVo);

    int count(OffsetSearchVo searchVo);


}
