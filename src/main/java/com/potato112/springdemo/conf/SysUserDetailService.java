package com.potato112.springdemo.conf;


import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Returns current user Authority
 */
@Slf4j
@Service
public class SysUserDetailService implements UserDetailsService, UserDetailsPasswordService {


    private UsersService usersService;

    public SysUserDetailService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {

        if (StringUtils.isBlank(userName)) {
            throw new UsernameNotFoundException("user name cannot be empty");
        }

        UserDetailsAuthority userByName = usersService.getUserByName(userName);
        if (null == userByName) {
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("user details fetched: " + userByName.getPassword());
        return userByName;
    }
}
