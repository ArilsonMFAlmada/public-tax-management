package com.arilsondev.publictaxmanagement.services;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface ProductService {

    ProductResponse postProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    ProductResponse putProduct(ProductRequest productRequest,
                               Long productId);

    void deleteProduct(Long productId);

    Page<ProductResponse> getAllProducts(String productName,
                                         String productBrand,
                                         BigDecimal productPrice,
                                         String unitMeasurement,
                                         Integer offset,
                                         Integer limit);
}
