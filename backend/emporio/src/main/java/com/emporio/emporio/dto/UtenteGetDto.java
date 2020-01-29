package com.emporio.emporio.dto;

import com.emporio.emporio.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UtenteGetDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtenteGetDto {

    private String username;
    private Role role;
    private Boolean enabled;
}