package com.chuper.diplom.controller;

import com.chuper.diplom.service.RentalRecordService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/record")
public class RentalRecordController {

    private final RentalRecordService rentalRecordService;

    public RentalRecordController(RentalRecordService rentalRecordService) {
        this.rentalRecordService = rentalRecordService;
    }
}
