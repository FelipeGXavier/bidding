package com.licitacao.requests;

import javax.validation.constraints.NotNull;

public class UserRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    private boolean admin;

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}
