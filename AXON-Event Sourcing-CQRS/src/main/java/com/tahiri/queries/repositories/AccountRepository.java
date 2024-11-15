package com.tahiri.queries.repositories;

import com.tahiri.queries.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
