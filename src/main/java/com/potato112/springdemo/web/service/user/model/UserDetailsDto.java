package com.potato112.springdemo.web.service.user.model;

import com.potato112.springdemo.web.service.group.model.UserGroupDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class UserDetailsDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //private String selectedOrganizationId;
    private List<UserGroupDto> userGroups = new ArrayList<>();
}