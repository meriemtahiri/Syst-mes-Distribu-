package com.tahiri.microservicerestconnector;

import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import com.tahiri.microservicerestconnector.ENTITIES.Customer;
import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import com.tahiri.microservicerestconnector.REPOSITORIES.CompteRepo;
import com.tahiri.microservicerestconnector.REPOSITORIES.CustomerRepo;
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
    @Bean
    CommandLineRunner start(CompteRepo compteRepo, CustomerRepo customerRepo, RepositoryRestConfiguration restConfiguration){
        return args -> {
            // Expose ids for Compte
            restConfiguration.exposeIdsFor(Compte.class);

            // Save customers first
            Customer customer1 = new Customer(null, "Customer 1", null);
            Customer customer2 = new Customer(null, "Customer 2", null);
            Customer customer3 = new Customer(null, "Customer 3", null);

            customerRepo.save(customer1);
            customerRepo.save(customer2);
            customerRepo.save(customer3);

            // Now save Compte entities and associate with the saved customers
            compteRepo.save(new Compte(null, 98000, new Date(), AccountType.COURANT, customer1));
            compteRepo.save(new Compte(null, 12000, new Date(), AccountType.EPARGNE, customer2));
            compteRepo.save(new Compte(null, 2100, new Date(), AccountType.COURANT, customer3));

            // Print comptes
            compteRepo.findAll().forEach(cp -> {
                System.out.println(cp.getType());
                System.out.println(cp.getSolde());
                System.out.println(cp.getCustomer().getName());  // Print associated customer name
            });
        };
    }


}
