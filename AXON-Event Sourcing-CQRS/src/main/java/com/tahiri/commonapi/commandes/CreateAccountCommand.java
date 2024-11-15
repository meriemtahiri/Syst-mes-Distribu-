package com.tahiri.commonapi.commandes;

import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand<String>{
 private final double initialBalance;
 private final String currency;

    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
