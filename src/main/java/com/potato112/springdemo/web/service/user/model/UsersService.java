package com.potato112.springdemo.web.service.user.model;

import com.potato112.springdemo.web.service.search.model.OffsetSearchDto;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.ui.user.UserFormParametersDto;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;

import java.util.Collection;
import java.util.Optional;

public interface UsersService {

    UserDetailsAuthority getUserByName(String userName);

    Optional<UserDto> getUser(String id);

    Collection<UserOverviewResponseDto> getUsers(OffsetSearchDto searchVo);

    int count(OffsetSearchDto searchVo);

    String create(UserDto userDto);

    UserDto update(UserDto userDto);

    UserFormParametersDto getUserFromParameters();

   /* TODO service other methods
    String create(UserVO userVO);
    UserVO update(UserVO userVO);
    Optional<UserVo> getUser(String id);
    */
}
