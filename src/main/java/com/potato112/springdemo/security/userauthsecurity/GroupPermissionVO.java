package com.potato112.springdemo.security.userauthsecurity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class GroupPermissionVO {

    private String id;

    //private UserGroup userGroupId;

    private ViewName viewName;

    private boolean canCreate;

    private boolean canUpdate;

    private boolean canDelete;

}
