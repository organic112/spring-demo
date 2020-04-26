package com.potato112.springdemo.security.userauthsecurity.model;

import com.potato112.springdemo.security.userauthsecurity.GroupPermissionVO;
import org.springframework.security.core.GrantedAuthority;


/**
 * Represents single user group granted Authority
 *  - every group can have multiple Authorities
 *  - user can have multiple groups
 */
public class UserGroupsAuthority implements GrantedAuthority {

    private GroupPermissionVO groupPermissionVO;

    public UserGroupsAuthority(GroupPermissionVO groupPermissionsVO) {
        this.groupPermissionVO = groupPermissionsVO;
    }

    /**
     * Returns basic Authority. View name means has authority to access and read
     */
    @Override
    public String getAuthority() {

        String authoritySting = getPermittedViewName(groupPermissionVO);
        System.out.println("ECHO AAAA fetched authority: " + authoritySting);
        return authoritySting;
    }

    public boolean canCreate() {
        return groupPermissionVO.isCanCreate();
    }

    public boolean canUpdate() {
        return groupPermissionVO.isCanUpdate();
    }

    public boolean canDelete() {
        return groupPermissionVO.isCanDelete();
    }

    private String getPermittedViewName(GroupPermissionVO groupPermissionVO) {
        String viewName = groupPermissionVO.getViewName().getEnumValue();
        return viewName;
    }
}
