package com.chuper.diplom.entity;

import com.chuper.diplom.entity.enums.RentRecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class RentalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account guestAccount;

    private Timestamp checkInDate;

    private Timestamp checkOutDate;

    private RentRecordStatus recordStatus;

    public RentalRecord() {
    }
}
