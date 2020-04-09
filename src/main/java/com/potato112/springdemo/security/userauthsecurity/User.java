package com.potato112.springdemo.security.userauthsecurity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface User extends Serializable {

    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUserName();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialNonExpired();

    boolean isEnabled();
}
