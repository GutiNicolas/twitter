package com.gutinicolas.twitter.model.api;

import javax.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String desiredUsername;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public String getDesiredUsername() {
        return desiredUsername;
    }

    public void setDesiredUsername(String desiredUsername) {
        this.desiredUsername = desiredUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
