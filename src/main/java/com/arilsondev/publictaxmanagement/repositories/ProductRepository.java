package com.arilsondev.publictaxmanagement.repositories;

import com.arilsondev.publictaxmanagement.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            "SELECT p FROM product p WHERE" +
                    "(:productName IS NULL OR p.productName = :productName) AND " +
                    "(:productBrand IS NULL OR p.productBrand = :productBrand) AND " +
                    "(:productPrice IS NULL OR p.productPrice = :productPrice) AND " +
                    "(:unitMeasurement IS NULL OR p.unitMeasurement = :unitMeasurement)"
    )
    Page<Product> findAllWithFilters(
            @Param("productName") String productName,
            @Param("productBrand") String productBrand,
            @Param("productPrice") BigDecimal productPrice,
            @Param("unitMeasurement") String unitMeasurement,
            Pageable pageRequest
    );

}
