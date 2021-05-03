package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.enums.RentRecordStatus;

import java.sql.Timestamp;

public class RentalRecordDto {
    private Long rentalRecordId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private RentRecordStatus recordStatus;

    public RentalRecordDto() {
    }

    public Long getRentalRecordId() {
        return rentalRecordId;
    }

    public void setRentalRecordId(Long rentalRecordId) {
        this.rentalRecordId = rentalRecordId;
    }



    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RentRecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RentRecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
