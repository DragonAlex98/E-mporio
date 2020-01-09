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
public class RegistrazioneAttivitaDto implements Serializable {

	private static final long serialVersionUID = 9879883378211L;

	@NotBlank
	private String shopPIVA;
	
	@NotBlank
	private String shopAddress;
	
	@NotBlank
	private String shopBusinessName;
	
	@NotBlank
	private String shopHeadquarter;
	
	@NotBlank
	private String shopCategoryDescription;
}