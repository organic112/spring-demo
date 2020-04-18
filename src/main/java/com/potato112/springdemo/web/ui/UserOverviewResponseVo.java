package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.security.userauthsecurity.service.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserOverviewResponseVo {

    String id;
    String email;
    String firstName;
    String lastName;
    List<String> groups;
    String phone;
    UserStatus locked;
}
