package com.tahiri.commonapi.events;

import com.tahiri.commonapi.enums.AccountStatus;
import lombok.Getter;

@Getter
public class AccountActivatedEvent extends BaseEvent<String> {
    private final AccountStatus status;
    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.status = status;
    }
}
