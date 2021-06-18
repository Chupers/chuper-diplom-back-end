package com.chuper.diplom.entity;

import com.chuper.diplom.entity.enums.RentRecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class RentalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalRecordId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Account guestAccount;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String accommodationName;

    private RentRecordStatus recordStatus;

    public RentalRecord() {
    }

    public RentalRecord(Accommodation accommodation, Account guestAccount, LocalDate checkInDate, LocalDate checkOutDate) {
        this.accommodation = accommodation;
        this.guestAccount = guestAccount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Long getRentalRecordId() {
        return rentalRecordId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public void setRentalRecordId(Long rentalRecordId) {
        this.rentalRecordId = rentalRecordId;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Account getGuestAccount() {
        return guestAccount;
    }

    public void setGuestAccount(Account guestAccount) {
        this.guestAccount = guestAccount;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RentRecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RentRecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
