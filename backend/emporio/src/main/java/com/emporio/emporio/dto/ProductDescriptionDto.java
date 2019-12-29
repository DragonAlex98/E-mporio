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
public class ProductDescriptionDto implements Serializable {
	
	private static final long serialVersionUID = 3248194637301940024L;

	@NotBlank
	private String productName;
	
	@NotBlank
	private String productCategoryName;
}