package com.isa.ISA.model.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    SYSADMIN("sysadmin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
