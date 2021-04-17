package com.chuper.diplom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AccommodationCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accommodationCharacteristicId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    private String iconName;

    private String characteristicText;

    public AccommodationCharacteristic() {
    }
}
