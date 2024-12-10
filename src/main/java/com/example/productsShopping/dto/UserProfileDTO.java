package com.example.productsShopping.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String email;

    public UserProfileDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
