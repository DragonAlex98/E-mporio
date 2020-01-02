package com.emporio.emporio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prodotto_descrizione")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoDescrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonIgnore
    private Integer productId;

    @Column(name = "Nome")
    @NotBlank
    private String productName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Categoria_Prodotto", nullable = false)
    @NotNull
    private CategoriaProdotto productCategory;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<RigaOrdineProdotto> orderProductsLineList;
}