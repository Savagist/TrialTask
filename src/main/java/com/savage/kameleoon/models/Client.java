package com.savage.kameleoon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 40, message = "Password should be between 8 and 40 characters")
    private String password;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    public Client() {

    }

    public Client(String name, String email, String password, LocalDateTime registrationDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
