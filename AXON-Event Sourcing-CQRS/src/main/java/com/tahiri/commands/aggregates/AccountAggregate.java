package com.tahiri.commands.aggregates;

import com.tahiri.commonapi.commandes.CreateAccountCommand;
import com.tahiri.commonapi.commandes.CreditAccountCommand;
import com.tahiri.commonapi.commandes.DebitAccountCommand;
import com.tahiri.commonapi.enums.AccountStatus;
import com.tahiri.commonapi.events.AccountActivatedEvent;
import com.tahiri.commonapi.events.AccountCreatedEvent;
import com.tahiri.commonapi.events.AccountCreditedEvent;
import com.tahiri.commonapi.events.AccountDebitedEvent;
import com.tahiri.commonapi.exceptions.AmountNegativeException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double initialBalance;
    private String currency;
    private AccountStatus accountStatus;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand.getInitialBalance() < 0)
            throw new RuntimeException("Cannot create account with negative balance !!");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.initialBalance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.accountStatus = AccountStatus.CREATED;
        this.accountId = event.getId();
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.accountStatus = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommand) {
        if (creditAccountCommand.getAmount() < 0) throw new AmountNegativeException("Amount should not be negative ");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                creditAccountCommand.getId(),
                creditAccountCommand.getAmount(),
                creditAccountCommand.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.initialBalance += event.getAmount();
    }


    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommand) {
        if (debitAccountCommand.getAmount() < 0) throw new AmountNegativeException("Amount should not be negative ");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                debitAccountCommand.getId(),
                debitAccountCommand.getAmount(),
                debitAccountCommand.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        this.initialBalance -= event.getAmount();
    }

}
