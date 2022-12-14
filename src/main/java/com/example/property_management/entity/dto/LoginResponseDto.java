package com.example.property_management.entity.dto;

import com.example.property_management.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private boolean auth;
    private String role;
}
