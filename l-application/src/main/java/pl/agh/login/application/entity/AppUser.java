package pl.agh.login.application.entity;

import lombok.Data;

import java.util.Set;

@Data
public class AppUser {
    private String username;
    private String password;
    private Boolean enabled;
    private Set<String> roles;
}