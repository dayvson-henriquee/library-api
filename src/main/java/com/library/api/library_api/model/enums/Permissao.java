package com.library.api.library_api.model.enums;

public enum Permissao {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;


    Permissao(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
