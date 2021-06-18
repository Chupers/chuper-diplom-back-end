package com.chuper.diplom.repository;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecord,Long> {
    List<RentalRecord> findByAccommodationAccommodationId(Long id);
}
