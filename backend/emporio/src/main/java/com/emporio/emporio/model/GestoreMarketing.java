package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("MARKETING")
public class GestoreMarketing extends User {

    public GestoreMarketing(String username, String password, Role role) {
        super(username, password, role);
    }
}