package com.arilsondev.publictaxmanagement.repositories;

import com.arilsondev.publictaxmanagement.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
