package com.potato112.springdemo.web.service.group;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GroupVO implements Serializable {

    private String id;
    private String groupName;
    private String parentGroupId;
    private String address;
}
