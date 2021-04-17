package com.chuper.diplom.service.impl;

import com.chuper.diplom.repository.RentalRecordRepository;
import com.chuper.diplom.service.RentalRecordService;
import org.springframework.stereotype.Service;

@Service
public class RentalRecordServiceImpl implements RentalRecordService {

    private final RentalRecordRepository rentalRecordRepository;

    public RentalRecordServiceImpl(RentalRecordRepository rentalRecordRepository) {
        this.rentalRecordRepository = rentalRecordRepository;
    }
}
