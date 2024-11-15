package com.tahiri.commonapi.events;

import lombok.Getter;

@Getter
public abstract class BaseEvent <T>{
    protected T id ;
    public BaseEvent(T id) {
        this.id = id;
    }
}
