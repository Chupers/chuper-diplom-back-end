package com.chuper.diplom.service.impl;

import com.chuper.diplom.entity.*;
import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.entity.dto.FeedbackDto;
import com.chuper.diplom.repository.AccommodationCharacteristicRepository;
import com.chuper.diplom.repository.AccommodationInfoRepository;
import com.chuper.diplom.repository.AccommodationRepository;
import com.chuper.diplom.service.AccommodationService;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.RentalRecordService;
import com.chuper.diplom.service.google.GoogleDriveService;
import com.google.api.client.util.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
        accountService.saveAccount(account);
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
        Accommodation accommodation = accommodationRepository.save(accommodationInfo.getAccommodation());
        accommodation.setAvailable(true);
        accommodationRepository.save(accommodation);
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
    public List<AccommodationDto> searchBySubString(String string) {
        List<AccommodationInfo> accommodationInfos = Lists.newArrayList(accommodationInfoRepository.findAll(QAccommodationInfo.accommodationInfo.address.containsIgnoreCase(string)
                .or(QAccommodationInfo.accommodationInfo.city.containsIgnoreCase(string)
                        .or(QAccommodationInfo.accommodationInfo.country.containsIgnoreCase(string))
                        .or(QAccommodationInfo.accommodationInfo.name.containsIgnoreCase(string))
                )));
        List<AccommodationDto> accommodationDtoList = Lists.newArrayList();
        accommodationInfos = accommodationInfos.stream().filter(accommodationInfo -> accommodationInfo.getAccommodation().getAvailable()).collect(Collectors.toList());
        for (AccommodationInfo accommodationInfo: accommodationInfos) {
            AccommodationDto accommodationDto = mapper.map(accommodationInfo.getAccommodation(),AccommodationDto.class);
            getReviewByAccommodationId(accommodationDto);
            accommodationDto.setFavorite(checkIsFavorite(accommodationInfo.getAccommodation().getAccommodationId()));
            accommodationDtoList.add(accommodationDto);
        }
        return accommodationDtoList;
    }

    @Override
    public AccommodationDto findAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.getOne(id);
        AccommodationDto accommodationDto = mapper.map(accommodation,AccommodationDto.class);
        accommodationDto.setFavorite(checkIsFavorite(accommodation.getAccommodationId()));
        getReviewByAccommodationId(accommodationDto);
        return accommodationDto;

    }
    public void getReviewByAccommodationId(AccommodationDto accommodation) {
        List<FeedbackDto> feedbackList = accommodation.getFeedbackList();
        if(feedbackList == null || feedbackList.isEmpty()){
            accommodation.setRating((double) 0);
            accommodation.setCountReview(0);
        }
        int countReview = feedbackList.size();
        AtomicReference<Integer> summaryStarCount = new AtomicReference<>(0);
        feedbackList.forEach(feedbackDto -> summaryStarCount.updateAndGet(v -> v + feedbackDto.getCountStar()));
        accommodation.setCountReview(countReview);
        double value = ( (double) summaryStarCount.get() / (double) countReview);
        double scale = Math.pow(10,2);
        accommodation.setRating(Math.ceil(value * scale) / scale);
    }

    private boolean checkIsFavorite(Long id){
        Account account = accountService.getActiveAccount();

        return  account!= null &&
                account.getFavoriteAccommodationId()!= null &&
                account.getFavoriteAccommodationId().contains(id);
    }

    @Override
    public AccommodationDto addCharacteristic(Long id, String icon, String text) {
        Accommodation accommodation = accommodationRepository.getOne(id);
        AccommodationCharacteristic accommodationCharacteristic = new AccommodationCharacteristic(accommodation,icon,text);
        accommodationCharacteristicRepository.save(accommodationCharacteristic);
        accommodation.getAccommodationCharacteristicList().add(accommodationCharacteristic);
        return mapper.map(accommodationRepository.save(accommodation), AccommodationDto.class);
    }

    @Override
    public Accommodation getById(Long id) {
        return accommodationRepository.getOne(id);
    }

    @Override
    public AccommodationDto saveAccommodation(Accommodation accommodation) {
        return mapper.map(accommodationRepository.save(accommodation),AccommodationDto.class);
    }

    @Override
    public AccommodationDto markFavoriteAccommodation(Long id) {
        Account account = accountService.getActiveAccount();
        if(account.getFavoriteAccommodationId()!= null
                && !account.getFavoriteAccommodationId().remove(id)){
            account.getFavoriteAccommodationId().add(id);
        }
        accountService.saveAccount(account);
        return findAccommodationById(id);
    }

    @Override
    public List<AccommodationDto> getFavoriteAccommodation() {
        return accountService.getActiveAccount().getFavoriteAccommodationId()
                .stream()
                .map(this::findAccommodationById)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccommodationDto> findAccommodation(String subString, BigInteger minPrice, BigInteger maxPrice, String type, LocalDate startDate, LocalDate endDate) {
        List<AccommodationDto> accommodationDtoList = searchBySubString(subString);
        if(accommodationDtoList == null){
            return null;
        }

        accommodationDtoList = accommodationDtoList.stream()
                .filter(accommodationDto -> accommodationDto.getAccommodationInfo().getPricePerDay().toBigInteger().compareTo(maxPrice) <= 0 &&
                        accommodationDto.getAccommodationInfo().getPricePerDay().toBigInteger().compareTo(minPrice) >= 0)
                .filter(accommodationDto -> accommodationDto.getAccommodationInfo().getAccommodationType().toUpperCase().equals(type))
                .collect(Collectors.toList());

        return accommodationDtoList;

    }


}
