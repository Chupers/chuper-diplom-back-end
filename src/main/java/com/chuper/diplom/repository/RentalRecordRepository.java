package com.chuper.diplom.repository;

import com.chuper.diplom.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecord,Long> {
}
