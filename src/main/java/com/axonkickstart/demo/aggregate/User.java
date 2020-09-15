package com.axonkickstart.demo.aggregate;

import com.axonkickstart.demo.command.AddAddressCommand;
import com.axonkickstart.demo.command.RegisterUserCommand;
import com.axonkickstart.demo.events.AddressAddedEvent;
import com.axonkickstart.demo.events.UserCreatedEvent;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * aggregate representing a user domain
 */
@Aggregate
@Getter
public class User {
    @AggregateIdentifier
    private Integer userId;
    private String name;
    private List<String> addresses;

    protected User(){
    //For Axon Instantiation
    }

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        Assert.notNull(registerUserCommand.getUserId(), "Id should not be null");
        Assert.notNull(registerUserCommand.getUserId(), "Name should not be null");
        AggregateLifecycle.apply(new UserCreatedEvent(registerUserCommand.getUserId()
                , registerUserCommand.getName()));
    }
    @EventSourcingHandler
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        userId = event.getUserId();
        name = event.getName();
        addresses = new ArrayList<>();
    }

    @CommandHandler
    public void addAddress(AddAddressCommand addAddressCommand) {
        Assert.notNull(addAddressCommand.getUserId(), "Id should not be null");
        Assert.notNull(addAddressCommand.getAddressName(), "Address should not be empty");
        AggregateLifecycle.apply(new AddressAddedEvent(addAddressCommand.getUserId(),
                addAddressCommand.getAddressName()));
    }

    @EventSourcingHandler
    public void handleAddressAddedEvent(AddressAddedEvent event) {
        addresses.add(event.getAddressName());
    }
}
