package com.emporio.emporio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    
	private static final long serialVersionUID = -6986746375915710855L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	private String role;
}