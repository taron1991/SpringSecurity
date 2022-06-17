package com.example.securityproject.roles;

public enum Permisson {
    DEV_READ("dev:read"),
    DEV_WRITE("dev:write");

    private final String permission;

    Permisson(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
