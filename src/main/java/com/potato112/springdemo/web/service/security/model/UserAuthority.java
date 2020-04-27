package com.potato112.springdemo.web.service.security.model;

import com.potato112.springdemo.web.service.group.GroupPermissionVO;
import org.springframework.security.core.GrantedAuthority;


/**
 * Represents user granted Authority (form user member groups)
 * - every group can have multiple Authorities
 * - user can have multiple groups
 */
public class UserAuthority implements GrantedAuthority {

    private GroupPermissionVO groupPermissionVO;

    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;


    public UserAuthority() {
    }

    public UserAuthority(GroupPermissionVO groupPermissionsVO) {
        this.groupPermissionVO = groupPermissionsVO;
        canCreate = groupPermissionsVO.isCanCreate();
        canUpdate = groupPermissionsVO.isCanUpdate();
        canDelete = groupPermissionsVO.isCanDelete();
    }

    /**
     * Returns basic Authority. View name means has authority to access and read
     */
    @Override
    public String getAuthority() {

        String authoritySting = getPermittedViewName();
        System.out.println("ECHO AAAA fetched authority: " + authoritySting);
        return authoritySting;
    }

    public boolean canCreate() {
        return canCreate;
    }

    public boolean canUpdate() {
        return canUpdate;
    }

    public boolean canDelete() {
        return canDelete;
    }

    public String getPermittedViewName() {

        if (null != groupPermissionVO.getViewName()) {
            return groupPermissionVO.getViewName().getEnumValue();
        }
        return "DEFAULT_VIEW_NAME";
    }
}
