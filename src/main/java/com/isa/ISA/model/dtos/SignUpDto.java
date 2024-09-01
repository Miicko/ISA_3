package com.isa.ISA.model.dtos;

import com.isa.ISA.model.enums.UserRole;

public record SignUpDto(
        String email,
        String password,
        UserRole role,
        String firstName,
        String lastName,
        String phoneNumber,
        String city,
        String country,
        String occupation,
        String companyInfo) {
}
