package com.potato112.springdemo.web.service.security.model;

import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import org.springframework.security.core.GrantedAuthority;


/**
 * Represents user granted Authority (form user member groups)
 * - every group can have multiple Authorities
 * - user can have multiple groups
 */
public class UserAuthorityVo implements GrantedAuthority {

    private GroupPermissionDto groupPermissionDto;

    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;


    public UserAuthorityVo() {
    }

    public UserAuthorityVo(GroupPermissionDto groupPermissionsVO) {
        this.groupPermissionDto = groupPermissionsVO;
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

        if (null != groupPermissionDto.getViewName()) {
            return groupPermissionDto.getViewName().getEnumValue();
        }
        return "DEFAULT_VIEW_NAME";
    }
}
