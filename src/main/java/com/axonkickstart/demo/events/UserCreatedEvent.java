package com.axonkickstart.demo.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * event to be executed for the user command
 */
@Data
public class UserCreatedEvent {
    @TargetAggregateIdentifier
    private final Integer userId;
    private final String name;
}
