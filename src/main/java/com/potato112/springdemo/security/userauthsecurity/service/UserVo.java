package com.potato112.springdemo.security.userauthsecurity.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Provides validation for user from
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVo implements Serializable {

    public static final class AttributeName {

        private AttributeName() {
        }

        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String USER_GROUPS = "userGroups";
        public static final String PHONE = "phone";
        public static final String LOCKED = "locked";
    }


    private String id;

    @Email(message = "provide valid email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "first name should not be empty")
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    //  @Valid
    //  @Size(min = 1, message = "Please add at least one Group")
    //  private List<UserGroupVO> userGroups;

    @Size(max = 20)
    private String phone;

    private UserStatus locked = UserStatus.ENABLED;

}
