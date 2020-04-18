package com.potato112.springdemo.security.userauthsecurity.service;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum  UserStatus {

    DISABLED("Locked"), ENABLED("Unlocked");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public static Optional<UserStatus> findByType(String name) {
        UserStatus[] values = values();
        for (UserStatus type : values) {
            if (type.name().equalsIgnoreCase(name)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
