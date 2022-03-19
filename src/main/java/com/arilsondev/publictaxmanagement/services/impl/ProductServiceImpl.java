package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.exceptions.ObjectNotFoundException;
import com.arilsondev.publictaxmanagement.repositories.ProductRepository;
import com.arilsondev.publictaxmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse postProduct(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.productRequestToProduct());

        return product.toProductResponse();
    }

    @Override
    public Page<ProductResponse> getAllProducts(
            String productName,
            String productBrand,
            BigDecimal productPrice,
            String unitMeasurement,
            Integer offset,
            Integer limit) {
        Pageable pageRequest = PageRequest.of(offset, limit, Sort.by("id"));

        return productRepository.findAllWithFilters(
                isNull(productName) ? null : productName,
                isNull(productBrand) ? null : productBrand,
                isNull(productPrice) ? null : productPrice,
                isNull(unitMeasurement) ? null : unitMeasurement,
                pageRequest)
               .map(Product::toProductResponse);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = getProduct(productId);

        return product.toProductResponse();
    }

    @Override
    public ProductResponse putProduct(ProductRequest productRequest, Long productId) {
        Product product = getProduct(productId);
        product.setId(productId);
        product.setProductName(productRequest.getProductName());
        product.setProductBrand(productRequest.getProductBrand());
        product.setProductPrice(productRequest.getProductPrice());
        product.setUnitMeasurement(productRequest.getUnitMeasurement());

        Product productToBeSaved = productRepository.save(product);

        return productToBeSaved.toProductResponse();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product not found! ProductId: " + productId + ", Type: " + Product.class.getSimpleName()));
    }
}
