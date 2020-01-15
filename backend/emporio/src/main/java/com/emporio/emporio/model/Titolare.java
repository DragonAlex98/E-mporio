package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@DiscriminatorValue("OWNER")
public class Titolare extends User {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_shop_owned_id", nullable = true)
    @JsonIgnore
    private Attivita shopOwned;

    public Titolare(String username, String password, Role role) {
        super(username, password, role);
    }
}