package com.chuper.diplom.service;

import com.chuper.diplom.entity.RentalRecord;
import com.chuper.diplom.entity.dto.RentalRecordDto;
import com.chuper.diplom.entity.enums.RentRecordStatus;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface RentalRecordService {
    Boolean checkRentalAvailability(Long flatId, LocalDate startDate, LocalDate endDate);
    RentalRecord saveRentalRecord(Long accomodationId, LocalDate checkInDate, LocalDate checkOutDate);
}
