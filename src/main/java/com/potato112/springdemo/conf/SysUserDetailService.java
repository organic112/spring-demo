package com.potato112.springdemo.conf;


import com.potato112.springdemo.security.userauthsecurity.UserDetailsVO;
import com.potato112.springdemo.security.userauthsecurity.UserGroupVO;
import com.potato112.springdemo.security.userauthsecurity.model.GroupType;
import com.potato112.springdemo.security.userauthsecurity.model.Roles;
import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * Returns current user Authority
 */
@Slf4j
@Service
public class SysUserDetailService implements UserDetailsService, UserDetailsPasswordService {

    // TODO some UsersService
    //private UserService userService;
/*    public SysUserDetailService(UserService userService) {
        this.userService = userService;
    }*/

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {

        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("user name cannot be empty");
        }
        // FIXME
        // UserDetailsAuthority userByName = userService.getUserByName(userName)
        /*  if(null == userByName){
            throw new RuntimeException("User not found");
        }
        return userByName  */
        log.info("Get mocked authorities by user name");
        return getUserServiceMockedAuthorityByName();
    }

    // MOCKED AUTHORITIES form WebSecurityService
    private UserDetailsAuthority getUserServiceMockedAuthorityByName() {

        UserDetailsAuthority userDetailsAuthority = new UserDetailsAuthority();

        UserGroupVO userGroupVO = new UserGroupVO();
        userGroupVO.setGroupType(GroupType.OWNER);
        userGroupVO.setRoles(Arrays.asList(Roles.ADMIN, Roles.MANAGER, Roles.USER));

        UserDetailsVO userDetailsVO = new UserDetailsVO();
        userDetailsVO.setEmail("admin"); //test@email.com
        userDetailsVO.setPassword("98ACDA0612B5263009C0E9F605F6844B8DAFF5AE");
        userDetailsVO.setFirstName("admin");
        userDetailsVO.setLastName("admin");
        userDetailsVO.setUserGroups(Arrays.asList(userGroupVO));

        userDetailsVO.setSelectedOrganizationId("aaabbbcccddd"); // FIXME
        userDetailsAuthority.setUserDetailsVO(userDetailsVO);

        log.info("Try for user login(e-mail): " + userDetailsVO.getEmail());
        return userDetailsAuthority;
    }
}
