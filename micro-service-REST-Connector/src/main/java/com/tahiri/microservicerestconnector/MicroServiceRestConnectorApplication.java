package com.tahiri.microservicerestconnector;

import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import com.tahiri.microservicerestconnector.REPOSITORIES.CompteRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class MicroServiceRestConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceRestConnectorApplication.class, args);
    }

    //TEST DTO
    //@Bean
    CommandLineRunner start(CompteRepo compteRepo, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Compte.class);
            compteRepo.save(new Compte(null,98000,new Date(), AccountType.COURANT));
            compteRepo.save(new Compte(null,12000,new Date(), AccountType.EPARGNE));
            compteRepo.save(new Compte(null,2100,new Date(), AccountType.COURANT));
            compteRepo.findAll().forEach(cp->{
                System.out.println(cp.getType());
                System.out.println(cp.getSolde());
            });
        };
    }

}
