package com.tahiri.bilingservice.ENTITIES;

import com.tahiri.bilingservice.MODELS.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Status status;
    Long costumerId;
    Date createdAt;
    Double total;
    @OneToMany(mappedBy = "bill")
    List<ProductItem> productItems;

    // we can do that using dto , this annotation tells JPA don't care about this attribute
    @Transient
    Customer customer;

    public Double getTotal() {
        Double total = 0.0;
        for (ProductItem productItem : getProductItems()) {
            total += productItem.getAmount();
        }
        return total;
    }
}
