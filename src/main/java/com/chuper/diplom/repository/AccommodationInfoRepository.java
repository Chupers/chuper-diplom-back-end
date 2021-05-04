package com.chuper.diplom.repository;

import com.chuper.diplom.entity.AccommodationInfo;
import com.chuper.diplom.entity.QAccommodation;
import com.chuper.diplom.entity.QAccommodationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationInfoRepository extends AccommodationInfoJQLRepository<AccommodationInfo,QAccommodationInfo,Long> {
}
