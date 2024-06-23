package com.example.salhuman.security.dto;


import lombok.Data;
import java.util.Date;

@Data
public class UserResponse {
    private Integer Id;
    private String email;
    private String name;
    private String password;
    private String role;
}
