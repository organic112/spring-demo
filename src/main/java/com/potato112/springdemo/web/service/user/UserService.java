package com.potato112.springdemo.web.service.user;

import com.potato112.springdemo.web.service.OffsetSearchDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersVo;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;

import java.util.Collection;

public interface UserService {



    String create(UserVo userVo);

    UserDetailsAuthority getUserByName(String userName);

    Collection<UserOverviewResponseDto> getUsers(OffsetSearchDto searchVo);

    int count(OffsetSearchDto searchVo);

    UserFormParametersVo getUserFromParameters();

    // TODO service other methods
   /* String create(UserVO userVO);

    UserVO update(UserVO userVO);

    Optional<UserVo> getUser(String id);

    ......
    */
}
