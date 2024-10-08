package com.tahiri.microservicerestconnector.MAPPERS;

import com.tahiri.microservicerestconnector.DTOS.CompteDto;
import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {

    CompteDto toCompteDto(Compte compte);

    Compte toCompte(CompteDto compteDto);
}

