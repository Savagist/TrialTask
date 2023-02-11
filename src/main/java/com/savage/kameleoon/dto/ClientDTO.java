package com.savage.kameleoon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClientDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 40, message = "Password should be between 8 and 40 characters")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
