package com.chuper.diplom.service.impl;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.RentalRecord;
import com.chuper.diplom.entity.dto.RentalRecordDto;
import com.chuper.diplom.entity.enums.RentRecordStatus;
import com.chuper.diplom.repository.RentalRecordRepository;
import com.chuper.diplom.service.AccommodationService;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.RentalRecordService;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class RentalRecordServiceImpl implements RentalRecordService {

    private final RentalRecordRepository rentalRecordRepository;
    private final DozerBeanMapper mapper;
    private final AccommodationService accommodationService;
    private final AccountService accountService;

    public RentalRecordServiceImpl(RentalRecordRepository rentalRecordRepository, AccommodationService accommodationService, AccountService accountService) {
        this.rentalRecordRepository = rentalRecordRepository;
        this.accommodationService = accommodationService;
        this.accountService = accountService;
        this.mapper = new DozerBeanMapper();
    }

    /*
    * Returns true if date period is free
    */
    @Override
    public Boolean checkRentalAvailability(Long flatId, LocalDate startDate, LocalDate endDate) {
        List<RentalRecord> rentalRecords = rentalRecordRepository.findByAccommodationAccommodationId(flatId);
        if (rentalRecords.isEmpty()){
            return true;
        }
        List<RentalRecord> filteredRecords = rentalRecords.stream().filter(rentalRecord ->
                rentalRecord.getCheckOutDate().isBefore(startDate)
                        || rentalRecord.getCheckInDate().isAfter(endDate)).collect(Collectors.toList());
        return !filteredRecords.isEmpty();
    }

    @Override
    public RentalRecord saveRentalRecord(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        if(checkRentalAvailability(accommodationId,checkInDate,checkOutDate)){
            Accommodation accommodation = accommodationService.getById(accommodationId);
            Account activeAccount = accountService.getActiveAccount();
            RentalRecord rentalRecord = new RentalRecord(accommodation, activeAccount, checkInDate, checkOutDate);
            rentalRecord.setRecordStatus(RentRecordStatus.NOT_PAID);
            rentalRecord.setAccommodationName(accommodation.getAccommodationInfo().getName());
            rentalRecord = rentalRecordRepository.save(rentalRecord);
            return rentalRecord;
        }
        return null;
    }

    @Override
    public List<RentalRecord> getRentalRecordByActiveUser() {
        return accountService.getActiveAccount().getRentalRecordList();
    }

    @Override
    public List<RentalRecord> getRentalByAccommodationId() {
        return rentalRecordRepository.findByAccommodationAccommodationId(
                accountService.getActiveAccount().getAccommodation().getAccommodationId());
    }

    @Override
    public void confirmRental(Long id) {
        RentalRecord rentalRecord = rentalRecordRepository.getOne(id);
        rentalRecord.setRecordStatus(RentRecordStatus.CONFIRM);
        rentalRecordRepository.save(rentalRecord);
    }
}
