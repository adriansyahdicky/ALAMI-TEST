package com.id.cooperative.repository;

import com.id.cooperative.entity.LoanApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    Page<LoanApplication> findByLoanDateBetween(LocalDate startDate, LocalDate finishDate, Pageable pageable);
    Page<LoanApplication> findByContactId(String contactId, Pageable pageable);
}
