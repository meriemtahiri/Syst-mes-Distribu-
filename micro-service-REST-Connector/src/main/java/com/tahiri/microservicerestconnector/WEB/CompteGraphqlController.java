package com.tahiri.microservicerestconnector.WEB;

import com.tahiri.microservicerestconnector.DTOS.CompteDto;
import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import com.tahiri.microservicerestconnector.SERVICES.CompteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompteGraphqlController {
    private final CompteService compteService;

    public CompteGraphqlController(CompteService compteService) {
        this.compteService = compteService;
    }

    @QueryMapping
    public List<CompteDto> comptesList() {
        return compteService.getAllComptes();
    }

    @QueryMapping
    public CompteDto getCompte(@Argument Long id) {
        return compteService.getCompte(id);
    }

    @MutationMapping
    public CompteDto createCompte(@Argument CompteDto input) {
        return compteService.saveCompte(input);
    }

    @MutationMapping
    public CompteDto updateCompte(@Argument Long id, @Argument CompteDto input) {
        return compteService.updateCompte(id, input);
    }


    @MutationMapping
    public Boolean deleteCompte(@Argument Long id) {
        compteService.deleteCompte(id);
        return true;
    }
}
