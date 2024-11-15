package com.tahiri.commonapi.commandes;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public  abstract class BaseCommand <T>{
  @TargetAggregateIdentifier

  protected T id ;

  public BaseCommand(T id) {
    this.id = id;
  }
}
