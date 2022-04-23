package com.id.cooperative.repository;

import com.id.cooperative.entity.SavingApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SavingApplicationRepository extends JpaRepository<SavingApplication, String> {
    Page<SavingApplication> findBySavingDateBetween(LocalDate startDate, LocalDate finishDate, Pageable pageable);
    Page<SavingApplication> findByContactId(String contactId, Pageable pageable);
}
