package com.chuper.diplom.service;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.dto.AccommodationDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.List;

public interface AccommodationService {
    AccommodationDto initAccommodation(String name, String type);
    AccommodationDto getAccommodationByActiveAccount();
    void updateAccommodationInfo(String city,
                                             String country,
                                             String name,
                                             String street,
                                             String description,
                                             Integer countGuest,
                                             Integer roomCount,
                                             Integer bedCount,
                                             BigDecimal price);

    AccommodationDto uploadPhoto(Long id, MultipartFile file) throws IOException, GeneralSecurityException;
    List<AccommodationDto> searchBySubString(String string);
    AccommodationDto findAccommodationById(Long id);
    AccommodationDto addCharacteristic(Long id, String icon, String text);
    Accommodation getById(Long id);
    AccommodationDto saveAccommodation(Accommodation accommodation);
}
