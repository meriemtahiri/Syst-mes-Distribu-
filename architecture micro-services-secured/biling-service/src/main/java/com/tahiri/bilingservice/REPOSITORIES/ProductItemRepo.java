package com.tahiri.bilingservice.REPOSITORIES;

import com.tahiri.bilingservice.ENTITIES.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductItemRepo extends JpaRepository<ProductItem, Long> {
    @RestResource(path = "/byCustomer")
    List<ProductItem> findByBillId(@Param("id") Long id);
}
