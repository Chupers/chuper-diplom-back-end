package com.chuper.diplom.controller;

import com.chuper.diplom.entity.RentalRecord;
import com.chuper.diplom.entity.dto.RentalRecordDto;
import com.chuper.diplom.entity.enums.RentRecordStatus;
import com.chuper.diplom.service.RentalRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/record")
public class RentalRecordController {

    private final RentalRecordService rentalRecordService;

    public RentalRecordController(RentalRecordService rentalRecordService) {
        this.rentalRecordService = rentalRecordService;
    }

    @PostMapping("/checkAvailability")
    public Boolean checkRentalAvailability(@RequestParam(name = "id") Long id,
                                           @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("startDate") LocalDate startDate,
                                           @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("endDate") LocalDate endDate) {
        return rentalRecordService.checkRentalAvailability(id, startDate, endDate);
    }

    @PostMapping()
    public RentalRecord addRentalRecord(@RequestParam("id") Long accommodationId,
                                           @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("startDate") LocalDate checkInDate,
                                           @DateTimeFormat(pattern = "MM.dd.yyyy") @RequestParam("endDate") LocalDate checkOutDate) {
        return rentalRecordService.saveRentalRecord(accommodationId, checkInDate, checkOutDate);
    }

    @GetMapping()
    public List<RentalRecord> getRentalRecordByActiveUser(){
        return rentalRecordService.getRentalRecordByActiveUser();
    }

    @GetMapping("/getRentalByAccommodationId")
    public List<RentalRecord> getRentalBcheckIsFavoriteyAccommodationId(){
        return rentalRecordService.getRentalByAccommodationId();
    }

    @PostMapping("/confirmRental")
    public void confirmRental(@RequestParam(name = "id") Long id){
        rentalRecordService.confirmRental(id);
    }
}
