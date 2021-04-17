package com.chuper.diplom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accommodationId;

    @OneToOne
    private AccommodationInfo accommodationInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<Feedback> feedbackList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<AccommodationCharacteristic> accommodationCharacteristicList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<RentalRecord> rentalRecordList;

    @OneToOne
    private Account account;

    public Accommodation() {
    }
}
