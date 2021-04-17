package com.chuper.diplom.service.impl;

import com.chuper.diplom.repository.AccommodationCharacteristicRepository;
import com.chuper.diplom.repository.AccommodationInfoRepository;
import com.chuper.diplom.repository.AccommodationRepository;
import com.chuper.diplom.service.AccommodationService;
import org.springframework.stereotype.Service;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationCharacteristicRepository accommodationCharacteristicRepository;
    private final AccommodationInfoRepository accommodationInfoRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, AccommodationCharacteristicRepository accommodationCharacteristicRepository, AccommodationInfoRepository accommodationInfoRepository) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationCharacteristicRepository = accommodationCharacteristicRepository;
        this.accommodationInfoRepository = accommodationInfoRepository;
    }
}
