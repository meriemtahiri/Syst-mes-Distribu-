package com.tahiri.microservicerestconnector.ENTITIES;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "prj1",types = Compte.class)
public interface CompteProjection1 {
    public Long getCode();
    public double getSolde();
}
