package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.sun.istack.NotNull;

import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public class AccommodationInfoDto {
    private Long accommodationInfoId;

    private String country;

    private String name;

    private String city;

    private String address;

    private Set<String> photo;

    private String longitude;

    private String latitude;

    private Integer guestCount;

    private Integer roomCount;

    private Integer bedCount;

    private String description;

    private String accommodationType;

    private BigDecimal pricePerDay;

    public AccommodationInfoDto() {
    }

    public Long getAccommodationInfoId() {
        return accommodationInfoId;
    }

    public void setAccommodationInfoId(Long accommodationInfoId) {
        this.accommodationInfoId = accommodationInfoId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<String> getPhoto() {
        return photo;
    }

    public void setPhoto(Set<String> photo) {
        this.photo = photo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getBedCount() {
        return bedCount;
    }

    public void setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
