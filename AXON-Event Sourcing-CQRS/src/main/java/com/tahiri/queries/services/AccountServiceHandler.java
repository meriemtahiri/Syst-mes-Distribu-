package com.tahiri.queries.services;

import com.tahiri.commonapi.events.AccountActivatedEvent;
import com.tahiri.commonapi.events.AccountCreatedEvent;
import com.tahiri.commonapi.events.AccountDebitedEvent;
import com.tahiri.commonapi.queries.GetAccountById;
import com.tahiri.commonapi.queries.GetAllAccountQuery;
import com.tahiri.queries.entities.Account;
import com.tahiri.queries.repositories.AccountRepository;
import com.tahiri.queries.repositories.OperationRepository;
import com.tahiri.commonapi.enums.OperationType;
import com.tahiri.commonapi.events.AccountCreditedEvent;
import com.tahiri.queries.entities.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class AccountServiceHandler {

    AccountRepository accountRepository;
    OperationRepository operationRepository;

    @EventHandler
    @Transactional
    public void on(AccountCreatedEvent event) {
        log.info("===========================");
        log.info("AccountCreatedEvent Received With T" +
                "he Following Infos : ");
        log.info(event.getId());
        log.info(event.getCurrency());
        log.info(event.getInitialBalance() + "");
        log.info("===========================");
        Account account = new Account();

        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    @Transactional
    public void on(AccountActivatedEvent event) {
        log.info("===========================");
        log.info("AccountActivatedEvent Received ");
        log.info("===========================");

        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    @Transactional
    public void on(AccountDebitedEvent event) {
        log.info("===========================");
        log.info("AccountDebitedEvent Received ");
        log.info("===========================");

        Operation operation = new Operation();
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmount());

        operation.setDataOperation(new Date());
        operation.setType(OperationType.DEBIT);
        operation.setAmount(event.getAmount());
        operation.setAccount(account);
        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @EventHandler
    @Transactional
    public void on(AccountCreditedEvent event) {
        log.info("===========================");
        log.info("AccountCreditedEvent Received ");
        log.info("===========================");

        Operation operation = new Operation();
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() + event.getAmount());
        operation.setDataOperation(new Date());
        operation.setType(OperationType.CREDIT);
        operation.setAmount(event.getAmount());
        operation.setAccount(account);
        operationRepository.save(operation);
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccountQuery getAllAccountQuery) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountById getAccountById) {
        return accountRepository.findById(getAccountById.getId()).get();
    }
}
