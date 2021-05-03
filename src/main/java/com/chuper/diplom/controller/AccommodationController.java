package com.chuper.diplom.controller;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.service.AccommodationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
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

    @GetMapping("/searchBySubString")
    public AccommodationDto getBySubString(@RequestParam(name = "subString") String subString){

    }
}
