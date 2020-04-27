package com.potato112.springdemo.web.service.user;

import com.potato112.springdemo.web.service.OffsetSearchVo;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersVo;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseVo;

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

    UserFormParametersVo getUserFromParameters();
}
