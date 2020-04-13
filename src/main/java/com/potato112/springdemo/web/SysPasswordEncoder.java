package com.potato112.springdemo.web;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.security.management.PasswordHash;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.GeneralSecurityException;


/**
 * encodes raw password to 'hashed password key'
 */
public class SysPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {

        if (StringUtils.isBlank(rawPassword)) {
            return null;
        }
        byte[] saltToByte = {};
        try {
            char[] passToChar = rawPassword.toString().toCharArray();
            PasswordHash passwordHash = new PasswordHash();
            return passwordHash.createPasswordKey(passToChar, saltToByte, 100);
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StringUtils.isBlank(rawPassword) || StringUtils.isBlank(encodedPassword)) {
            return false;
        }
        String encoded = encode(rawPassword);
        return encoded.equalsIgnoreCase(encodedPassword);
    }


}
