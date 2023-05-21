package com.devvy.backend.common.enums;

public enum AuthorityType {
    USER("USER", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    ANONYMOUS("ANONYMOUS", "ROLE_ANONYMOUS");

    private final String code;
    private final String role;

    AuthorityType(String code, String role) {
        this.code = code;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }
}
