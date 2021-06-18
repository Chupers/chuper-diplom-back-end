package com.chuper.diplom.controller;

import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.service.AccommodationService;
import com.chuper.diplom.service.RentalRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RentalRecordService rentalRecordService;

    public AccommodationController(AccommodationService accommodationService, RentalRecordService rentalRecordService) {
        this.accommodationService = accommodationService;
        this.rentalRecordService = rentalRecordService;
    }

    @PostMapping("/init")
    public AccommodationDto initAccommodation(@RequestParam(name = "name") String placeName,
                                              @RequestParam(name = "placeType") String placeType){
        return accommodationService.initAccommodation(placeName,placeType);
    }

    @GetMapping("/active")
    public AccommodationDto getActiveAccommodation(){
        return accommodationService.getAccommodationByActiveAccount();
    }

    @PostMapping("/updateBasicInfo")
    public void updateBasicInfo(@RequestParam(name = "city") String city,
                                            @RequestParam(name = "country") String country,
                                            @RequestParam(name = "name") String name,
                                            @RequestParam(name = "street") String street,
                                            @RequestParam(name = "description")String description,
                                            @RequestParam(name = "guestCount") Integer countGuest,
                                            @RequestParam(name = "roomCount") Integer roomCount,
                                            @RequestParam(name = "bedCount") Integer bedCount,
                                            @RequestParam(name = "price") BigDecimal price){
        accommodationService.updateAccommodationInfo(city, country, name, street, description, countGuest, roomCount, bedCount, price);
    }

    @PutMapping("/loadPhotoByIdGoogle")
    public AccommodationDto loadPhoto(@RequestParam Long id, @RequestParam MultipartFile file) throws IOException, GeneralSecurityException {
        return accommodationService.uploadPhoto(id,file);
    }

    @PostMapping("/searchBySubString")
    public List<AccommodationDto> getBySubString(@RequestParam(name = "subString") String subString){
        return accommodationService.searchBySubString(subString);
    }

    @PostMapping("/extraSearch")
    public List<AccommodationDto> search(@RequestParam(name = "subString") String subString,
                                         @RequestParam(name = "minPrice") BigInteger minPrice,
                                         @RequestParam(name = "maxPrice") BigInteger maxPrice,
                                         @RequestParam(name = "type") String type,
                                         @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("startDate") LocalDate startDate,
                                         @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("endDate") LocalDate endDate){
        return accommodationService.findAccommodation(subString,minPrice,maxPrice,type,startDate,endDate).stream()
                .filter(accommodationDto -> rentalRecordService.checkRentalAvailability(accommodationDto.getAccommodationId(),startDate,endDate)).collect(Collectors.toList());
    }

    @PutMapping("/addCharacteristic")

    public AccommodationDto addCharacteristic(@RequestParam(name = "id") Long id,
                                              @RequestParam(name = "text") String text,
                                              @RequestParam(name = "icon") String icon){
       return accommodationService.addCharacteristic(id,icon,text);
    }

    @PostMapping("/findById")
    public AccommodationDto getById(@RequestParam Long id){
        return accommodationService.findAccommodationById(id);
    }

    @PostMapping("/markFavorite")
    public AccommodationDto markFavorite(@RequestParam Long id){
        return accommodationService.markFavoriteAccommodation(id);

    }

    @GetMapping("/getFavorite")
    public List<AccommodationDto> getFavoriteAccommodation(){
        return accommodationService.getFavoriteAccommodation();
    }
}
