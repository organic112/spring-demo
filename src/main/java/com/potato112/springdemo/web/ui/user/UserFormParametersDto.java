package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.ui.constants.ViewName;
import com.potato112.springdemo.web.service.security.model.GroupType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class UserFormParametersDto implements Serializable {

    private Map<GroupType, List<ViewName>> availableRolesPerType;

}
