package com.potato112.springdemo.web.service.group.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Dto contains api response information for Groups Overview
 */
@Getter
@Setter
public class GroupOverviewResponseDto {

    String id;
    private String groupName;
    private List<GroupPermissionDto> groupPermissions = new ArrayList<>();
}
