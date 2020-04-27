package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.web.ui.constants.ViewName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class GroupPermissionDto {

    private String id;

    //private UserGroup userGroupId;

    private ViewName viewName;

    private boolean canCreate;

    private boolean canUpdate;

    private boolean canDelete;

}
