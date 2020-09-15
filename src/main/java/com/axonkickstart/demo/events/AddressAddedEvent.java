package com.axonkickstart.demo.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class AddressAddedEvent {
    @TargetAggregateIdentifier
    private final Integer userId;
    private final String addressName;
}
