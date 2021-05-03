package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class AccommodationDto {
    private Long accommodationId;
    private AccommodationInfoDto accommodationInfo;
    private List<AccommodationCharacteristicDto> accommodationCharacteristicList;

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public AccommodationInfoDto getAccommodationInfo() {
        return accommodationInfo;
    }

    public void setAccommodationInfo(AccommodationInfoDto accommodationInfo) {
        this.accommodationInfo = accommodationInfo;
    }

    public List<AccommodationCharacteristicDto> getAccommodationCharacteristicList() {
        return accommodationCharacteristicList;
    }

    public void setAccommodationCharacteristicList(List<AccommodationCharacteristicDto> accommodationCharacteristicList) {
        this.accommodationCharacteristicList = accommodationCharacteristicList;
    }
}
