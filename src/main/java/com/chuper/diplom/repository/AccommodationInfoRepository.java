package com.chuper.diplom.repository;

import com.chuper.diplom.entity.AccommodationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationInfoRepository extends JpaRepository<AccommodationInfo,Long> {
}
