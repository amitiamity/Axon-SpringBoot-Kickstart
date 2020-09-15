package com.axonkickstart.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private String addressName;
}
