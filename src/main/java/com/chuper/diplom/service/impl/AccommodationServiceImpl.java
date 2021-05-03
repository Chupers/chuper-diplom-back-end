package com.chuper.diplom.service.impl;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.AccommodationInfo;
import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.repository.AccommodationCharacteristicRepository;
import com.chuper.diplom.repository.AccommodationInfoRepository;
import com.chuper.diplom.repository.AccommodationRepository;
import com.chuper.diplom.service.AccommodationService;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.google.GoogleDriveService;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationCharacteristicRepository accommodationCharacteristicRepository;
    private final AccommodationInfoRepository accommodationInfoRepository;
    private final AccountService accountService;
    private final DozerBeanMapper mapper;


    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, AccommodationCharacteristicRepository accommodationCharacteristicRepository, AccommodationInfoRepository accommodationInfoRepository, AccountService accountService) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationCharacteristicRepository = accommodationCharacteristicRepository;
        this.accommodationInfoRepository = accommodationInfoRepository;
        this.accountService = accountService;
        mapper = new DozerBeanMapper();
    }

    @Override
    public AccommodationDto initAccommodation(String name, String type) {
        Accommodation accommodation = new Accommodation();
        accommodation.setAvailable(false);
        Account account = accountService.getActiveAccount();
        account.setHost(true);
        accommodation.setAccount(account);
        accommodation = accommodationRepository.save(accommodation);
        AccommodationInfo accommodationInfo = new AccommodationInfo();
        accommodationInfo.setName(name);
        accommodationInfo.setAccommodationType(type);
        accommodationInfo.setAccommodation(accommodation);
        accommodationInfo = accommodationInfoRepository.save(accommodationInfo);
        accommodation.setAccommodationInfo(accommodationInfo);
        account.setAccommodation(accommodation);
        return  mapper.map(accommodationRepository.save(accommodation),AccommodationDto.class);
    }

    @Override
    public AccommodationDto getAccommodationByActiveAccount() {
        return mapper.map(accountService.getActiveAccount().getAccommodation(),AccommodationDto.class);
    }

    @Override
    public void updateAccommodationInfo(String city, String country, String name, String street, String description, Integer countGuest, Integer roomCount, Integer bedCount, BigDecimal price) {
        AccommodationInfo accommodationInfo = accountService.getActiveAccount().getAccommodation().getAccommodationInfo();
        accommodationInfo.setCountry(country);
        accommodationInfo.setDescription(description);
        accommodationInfo.setCity(city);
        accommodationInfo.setName(name);
        accommodationInfo.setBedCount(bedCount);
        accommodationInfo.setPricePerDay(price);
        accommodationInfo.setGuestCount(countGuest);
        accommodationInfo.setRoomCount(roomCount);
        accommodationInfo.setAddress(street);
        accommodationInfoRepository.save(accommodationInfo);
    }

    @Override
    public AccommodationDto uploadPhoto(Long id, MultipartFile file) throws IOException, GeneralSecurityException {
        Accommodation accommodation = accommodationRepository.getOne(id);
        String imagePath = GoogleDriveService.GOOGLE_PATH + GoogleDriveService.saveImage(file).getId();
        accommodation.getAccommodationInfo().getPhoto().add(imagePath);
        accommodationInfoRepository.save(accommodation.getAccommodationInfo());
        return mapper.map(accommodation,AccommodationDto.class);
    }

    @Override
    public AccommodationDto searchBySubString(String string) {
        return null;
    }


}
