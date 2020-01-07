package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private static final long serialVersionUID = 5978577607185714714L;

    public Admin(String username, String password, Role role) {
        super(username, password, role);
    }
}