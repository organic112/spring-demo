package com.potato112.springdemo.security.userauthsecurity.authentication;

public class SysRole {

    private SysRole() {
    }

    public static final class SpecialistRole {
        public static final String USER = "ROLE_SPECIALIST_USER";

        private SpecialistRole() {
        }
    }

    public static final class DistributorRole {
        public static final String USER = "ROLE_DISTRIBUTOR_USER";
        public static final String MANAGER = "ROLE_DISTRIBUTOR_MANAGER";

        private DistributorRole() {
        }
    }

    public static final class OwnerRole {
        //public static final String USER = "ROLE_OWNER_USER";
        public static final String MANAGER = "ROLE_OWNER_MANAGER";
        public static final String ADMIN = "ROLE_OWNER_ADMIN";

        private OwnerRole() {
        }
    }

    public static String[] getAllRoles() {

        String[] ALL_ROLES = {
                SpecialistRole.USER,
                DistributorRole.USER,
                DistributorRole.MANAGER,
                OwnerRole.MANAGER,
                OwnerRole.ADMIN};
        return ALL_ROLES;
    }
}
