package com.tahiri.microservicerestconnector.REPOSITORIES;

import com.tahiri.microservicerestconnector.ENTITIES.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
