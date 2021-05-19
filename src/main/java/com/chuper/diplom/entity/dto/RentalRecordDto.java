package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.enums.RentRecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.time.LocalDate;

public class RentalRecordDto {
    private Long rentalRecordId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RentRecordStatus recordStatus;

    public RentalRecordDto() {
    }

    public Long getRentalRecordId() {
        return rentalRecordId;
    }

    public void setRentalRecordId(Long rentalRecordId) {
        this.rentalRecordId = rentalRecordId;
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
