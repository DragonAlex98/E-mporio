package com.emporio.emporio.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChangePasswordDto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @NotBlank
    private String oldPassword;
    
    @NotBlank
    private String newPassword;
    
    @NotBlank
    private String confirmNewPassword;
}