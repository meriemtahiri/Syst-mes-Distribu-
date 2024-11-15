package com.tahiri.queries.repositories;

import com.tahiri.queries.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
