package com.arilsondev.publictaxmanagement.repositories;

import com.arilsondev.publictaxmanagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
