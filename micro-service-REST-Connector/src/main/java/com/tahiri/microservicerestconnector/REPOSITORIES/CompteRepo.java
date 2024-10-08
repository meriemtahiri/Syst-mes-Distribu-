package com.tahiri.microservicerestconnector.REPOSITORIES;

import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompteRepo extends JpaRepository<Compte,Long> {
}
