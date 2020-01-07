package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OPERATOR")
public class OperatoreSistema extends User {
    private static final long serialVersionUID = -1692839639000020959L;

    public OperatoreSistema(String username, String password, Role role) {
        super(username, password, role);
    }
}