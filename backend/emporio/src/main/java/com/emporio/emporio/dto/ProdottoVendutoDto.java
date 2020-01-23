package com.emporio.emporio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.emporio.emporio.model.ProdottoDescrizione;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoVendutoDto implements Serializable {

    private static final long serialVersionUID = -5322802231392192764L;

    @NotNull
	private ProdottoDescrizione product;
	
	@NotNull
	private Integer quantity;
}