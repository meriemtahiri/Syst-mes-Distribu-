package com.tahiri.commonapi.events;

import com.tahiri.commonapi.enums.AccountStatus;
import lombok.Getter;

@Getter
public class AccountCreatedEvent extends BaseEvent<String> {

    private final double initialBalance;
    private final String currency;
    private final AccountStatus status;

    public AccountCreatedEvent(String id, double initialBalance, String currency, AccountStatus status) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
