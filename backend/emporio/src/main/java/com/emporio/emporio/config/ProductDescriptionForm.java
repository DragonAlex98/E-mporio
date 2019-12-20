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
public class ProductDescriptionForm implements Serializable {
	
	private static final long serialVersionUID = 3248194637301940024L;
	private String productName;
	private String productCategoryName;
}