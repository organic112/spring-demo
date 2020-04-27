/*
package com.potato112.springdemo.web.userauthsecurity.service;


import com.potato112.springdemo.web.service.user.UserDetailsVO;
import com.potato112.springdemo.web.service.group.UserGroupVO;
import com.potato112.springdemo.web.service.security.model.GroupType;
import com.potato112.springdemo.web.service.security.model.Roles;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

*/
/*
no used to remove
 *//*


@Slf4j
//@Service
public class SysUserService implements UserService {


    @Override
    public UserDetailsAuthority getUserByName(String userName) {
        log.info("Get mocked authorities by user name:" + userName);
        // FIXME
        if (userName.equals("admin")) {
            return getUserServiceMockedAuthorityByName();
        } else {
            return null;
        }
    }

    // FIXME MOCKED AUTHORITIES
    private UserDetailsAuthority getUserServiceMockedAuthorityByName() {

        UserDetailsAuthority userDetailsAuthority = new UserDetailsAuthority();

        UserGroupVO userGroupVO = new UserGroupVO();
        userGroupVO.setGroupType(GroupType.OWNER);
        List<Roles> roles = Arrays.asList(Roles.ADMIN, Roles.MANAGER, Roles.USER);
        userGroupVO.setRoles(roles);

*/
/*        UserDetailsVO userDetailsVO = new UserDetailsVO();
        userDetailsVO.setEmail("admin"); //test@email.com
        userDetailsVO.setPassword("98ACDA0612B5263009C0E9F605F6844B8DAFF5AE");
        userDetailsVO.setFirstName("admin");
        userDetailsVO.setLastName("admin");
        userDetailsVO.setUserGroups(Arrays.asList(userGroupVO));

        userDetailsVO.setSelectedOrganizationId("aaabbbcccddd"); // FIXME
        userDetailsAuthority.setUserDetailsVO(userDetailsVO);

        log.info("Try for user login(e-mail): " + userDetailsVO.getEmail());*//*


        return userDetailsAuthority;
    }
}


*/
