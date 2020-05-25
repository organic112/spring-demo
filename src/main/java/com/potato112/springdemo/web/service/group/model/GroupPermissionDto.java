package com.potato112.springdemo.web.service.group.model;

import com.potato112.springdemo.web.ui.constants.ViewName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class GroupPermissionDto extends BaseEntityDto {

    private String id;
    private ViewName viewName;
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;
}
