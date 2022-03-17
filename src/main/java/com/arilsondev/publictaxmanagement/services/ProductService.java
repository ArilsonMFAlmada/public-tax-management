package com.arilsondev.publictaxmanagement.services;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    ProductResponse postProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    ProductResponse putProduct(ProductRequest productRequest, Long productId);

    void deleteProduct(Long productId);

    Page<ProductResponse> getAllProducts(String productName, String productBrand, BigDecimal productPrice, LocalDate date, Integer offset, Integer limit);
}
