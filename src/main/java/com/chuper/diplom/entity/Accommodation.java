package com.chuper.diplom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accommodationId;

    @OneToOne
    private AccommodationInfo accommodationInfo;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<Feedback> feedbackList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<AccommodationCharacteristic> accommodationCharacteristicList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<RentalRecord> rentalRecordList;

    @JsonIgnore
    @OneToOne
    private Account account;

    @JsonIgnore
    private Boolean isAvailable;

    public Accommodation() {
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public AccommodationInfo getAccommodationInfo() {
        return accommodationInfo;
    }

    public void setAccommodationInfo(AccommodationInfo accommodationInfo) {
        this.accommodationInfo = accommodationInfo;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<AccommodationCharacteristic> getAccommodationCharacteristicList() {
        return accommodationCharacteristicList;
    }

    public void setAccommodationCharacteristicList(List<AccommodationCharacteristic> accommodationCharacteristicList) {
        this.accommodationCharacteristicList = accommodationCharacteristicList;
    }

    public List<RentalRecord> getRentalRecordList() {
        return rentalRecordList;
    }

    public void setRentalRecordList(List<RentalRecord> rentalRecordList) {
        this.rentalRecordList = rentalRecordList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
