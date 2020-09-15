package com.axonkickstart.demo.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * command to be executed against user aggregate
 */
@Data
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private final Integer userId;
    private final String name;
}
