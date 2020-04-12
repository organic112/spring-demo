package com.potato112.springdemo.security.userauthsecurity.model;

import com.potato112.springdemo.security.userauthsecurity.UserDetailsVO;
import com.potato112.springdemo.security.userauthsecurity.UserGroupVO;
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
    private boolean accountNotLocket;
    private boolean credentialsNotExpired;
    private boolean enabled;

    /**
     * Returns list of authorities granted with Roles from user groups.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<UserGroupVO> groups = userDetailsVO.getUserGroups();

        List<Roles> rolesInGroup = groups.stream()
                .map(UserGroupVO::getRoles)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //TODO some logic related to group type
        GroupType fixmeHardcodedType = GroupType.SPECIALIST;

        return rolesInGroup.stream()
                .map(role -> new GroupAuthority(fixmeHardcodedType, role.getEnumValue()))
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
