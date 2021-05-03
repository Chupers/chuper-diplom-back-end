package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;

public class AccommodationCharacteristicDto {
    private Long accommodationCharacteristicId;
    private String iconName;
    private String characteristicText;

    public Long getAccommodationCharacteristicId() {
        return accommodationCharacteristicId;
    }

    public void setAccommodationCharacteristicId(Long accommodationCharacteristicId) {
        this.accommodationCharacteristicId = accommodationCharacteristicId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getCharacteristicText() {
        return characteristicText;
    }

    public void setCharacteristicText(String characteristicText) {
        this.characteristicText = characteristicText;
    }
}
