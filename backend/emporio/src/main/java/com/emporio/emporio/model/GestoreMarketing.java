package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MARKETING")
public class GestoreMarketing extends User {
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_shop_works_for_id", nullable = true)
    @JsonIgnore
    private Attivita shopWorksFor;

    public GestoreMarketing(String username, String password, Role role) {
        super(username, password, role);
    }
}