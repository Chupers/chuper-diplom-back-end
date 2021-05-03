package com.chuper.diplom.repository;

import com.chuper.diplom.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    @Override
    Optional<Accommodation> findById(Long aLong);
}
