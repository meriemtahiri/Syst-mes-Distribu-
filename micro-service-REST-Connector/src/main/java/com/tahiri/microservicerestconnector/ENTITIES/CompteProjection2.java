package com.tahiri.microservicerestconnector.ENTITIES;

import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "prj2",types = Compte.class)
public interface CompteProjection2 {
    double getSolde();
    AccountType getType();
}
