package com.arilsondev.publictaxmanagement.services;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse postProduct(ProductRequest productRequest);

    Page<ProductResponse> getAllProducts(Integer offset, Integer limit);

    ProductResponse getProductById(Long productId);

    ProductResponse putProduct(ProductRequest productRequest, Long productId);

    void deleteProduct(Long productId);
}
