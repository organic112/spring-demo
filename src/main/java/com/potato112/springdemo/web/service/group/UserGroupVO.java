package com.potato112.springdemo.web.service.group;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class UserGroupVO implements Serializable {

    private String id;
    private String groupName;
    private List<GroupPermissionDto> groupPermissions;

}