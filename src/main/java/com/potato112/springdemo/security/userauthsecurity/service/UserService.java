package com.potato112.springdemo.security.userauthsecurity.service;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;

public interface UserService {

    // TODO service other methods
   /* String create(UserVO userVO);

    UserVO update(UserVO userVO);

    Optional<UserVo> getUser(String id);

    ......
    */

    UserDetailsAuthority getUserByName(String userName);

}
