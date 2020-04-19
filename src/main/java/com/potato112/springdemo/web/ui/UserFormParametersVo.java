package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.security.userauthsecurity.model.GroupType;
import com.potato112.springdemo.security.userauthsecurity.model.Roles;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class UserFormParametersVo implements Serializable {

    private Map<GroupType, List<Roles>> availableRolesPerType;


}
