package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.service.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserOverviewResponseDto implements Serializable {

    String id;
    String email;
    String firstName;
    String lastName;
    List<String> groups;
    String phone;
    UserStatus locked;
}
