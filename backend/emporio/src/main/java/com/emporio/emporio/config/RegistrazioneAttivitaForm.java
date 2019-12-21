package com.emporio.emporio.config;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrazioneAttivitaForm implements Serializable {

	private static final long serialVersionUID = 9879883378211L;
	private String shopPIVA;
	private String shopAddress;
	private String shopBusinessName;
	private String shopHeadquarter;
	private String shopCategory;
}