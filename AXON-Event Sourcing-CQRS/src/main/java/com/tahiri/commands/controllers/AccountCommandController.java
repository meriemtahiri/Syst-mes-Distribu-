package com.tahiri.commands.controllers;


import com.tahiri.commonapi.commandes.CreateAccountCommand;
import com.tahiri.commonapi.commandes.CreditAccountCommand;
import com.tahiri.commonapi.commandes.DebitAccountCommand;
import com.tahiri.commonapi.dtos.CreateAccountRequestDto;
import com.tahiri.commonapi.dtos.CreditAccountRequestDto;
import com.tahiri.commonapi.dtos.DebitAccountRequestDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/command/account")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDto request) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDto request) {
        return commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDto request) {
        return commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
    }

    @GetMapping(path = "/eventStore/{id}")
    public Stream<?> eventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> getErrorResponse(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
