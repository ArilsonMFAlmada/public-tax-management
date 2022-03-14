package com.arilsondev.publictaxmanagement.services;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse postProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    ProductResponse putProduct(ProductRequest productRequest, Long productId);

    void deleteProduct(Long productId);
}
