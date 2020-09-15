package com.axonkickstart.demo.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class AddAddressCommand {
    @TargetAggregateIdentifier
    private final Integer userId;
    private final String addressName;
}
