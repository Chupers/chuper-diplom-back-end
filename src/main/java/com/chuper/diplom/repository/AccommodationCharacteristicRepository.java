package com.chuper.diplom.repository;

import com.chuper.diplom.entity.AccommodationCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationCharacteristicRepository extends JpaRepository<AccommodationCharacteristic,Long> {
}
