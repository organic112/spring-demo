package com.potato112.springdemo.web.service.security.model;

import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.service.user.UserDetailsVO;
import com.potato112.springdemo.web.service.group.UserGroupVO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores information about user authority
 */
@Data
public class UserDetailsAuthority implements UserDetails {

    private UserDetailsVO userDetailsVO;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;


    /**
     * Returns list of authorities granted with Roles from user groups.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GroupPermissionDto> allUserGroupPermissions = userDetailsVO.getUserGroups().stream()
                .map(UserGroupVO::getGroupPermissions)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return allUserGroupPermissions.stream()
                .map(groupPermissionDto -> new UserAuthorityVo(groupPermissionDto))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDetailsVO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsVO.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
