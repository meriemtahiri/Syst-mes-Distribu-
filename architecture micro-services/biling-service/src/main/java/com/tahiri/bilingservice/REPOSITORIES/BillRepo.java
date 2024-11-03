package com.tahiri.bilingservice.REPOSITORIES;

import com.tahiri.bilingservice.ENTITIES.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
@RepositoryRestResource
public interface BillRepo extends JpaRepository<Bill, Long> {
    @RestResource(path = "/byCustomer")
    List<Bill> findByCostumerId(@Param("id") Long id);
}
