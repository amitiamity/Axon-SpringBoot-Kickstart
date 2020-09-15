package com.axonkickstart.demo.service;

import com.axonkickstart.demo.entity.AddressEntity;
import com.axonkickstart.demo.events.AddressAddedEvent;
import com.axonkickstart.demo.model.AddressBean;
import com.axonkickstart.demo.query.GetAddressQuery;
import com.axonkickstart.demo.repository.AddressRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AddressRepositoryProjector {

    @Autowired
    AddressRepository addressRepository;

    @EventHandler
    public void addAddress(AddressAddedEvent event) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressName(event.getAddressName());
        addressEntity.setUserId(event.getUserId());
        addressRepository.save(addressEntity);
    }

    @QueryHandler
    public List<AddressBean> getAddresses(GetAddressQuery query) {
        return addressRepository.findByUserId(query.getUserId())
                .stream()
                .map(toAddress())
                .collect(Collectors.toList());
    }

    private Function<AddressEntity, AddressBean> toAddress() {
        return  addressEntity -> {
            AddressBean addressBean = new AddressBean();
            addressBean.setAddress(addressEntity.getAddressName());
            addressBean.setId(addressEntity.getId());
            return addressBean;
        };
    }
}
