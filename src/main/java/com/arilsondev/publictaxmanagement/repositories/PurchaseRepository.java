package com.arilsondev.publictaxmanagement.repositories;

import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(
            "SELECT p FROM purchase p WHERE" +
                    "(:date IS NULL OR p.date = :date) AND " +
                    "(:cep IS NULL OR p.cep = :cep) AND " +
                    "(:city IS NULL OR p.city = :city) AND " +
                    "(:estate IS NULL OR p.estate = :estate)"
    )
    Page<Purchase> findAllWithFilters(
            @Param("date") LocalDate date,
            @Param("cep") String cep,
            @Param("city")String city,
            @Param("estate")String estate,
            Pageable pageRequest);
}
