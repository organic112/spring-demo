package com.potato112.springdemo.web.service.user;

import com.potato112.springdemo.web.service.OffsetSearchDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersDto;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;

import java.util.Collection;
import java.util.Optional;

public interface UsersService {


    String create(UserDto userDto);

    UserDetailsAuthority getUserByName(String userName);

    Collection<UserOverviewResponseDto> getUser(OffsetSearchDto searchVo);

    int count(OffsetSearchDto searchVo);

    UserFormParametersDto getUserFromParameters();

    Optional<UserDto> getUser(String id);

    UserDto update(UserDto userDto);


    // TODO service other methods
   /* String create(UserVO userVO);

    UserVO update(UserVO userVO);

    Optional<UserVo> getUser(String id);

    ......
    */
}
