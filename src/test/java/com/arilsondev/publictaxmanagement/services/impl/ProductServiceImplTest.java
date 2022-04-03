package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.exceptions.ObjectNotFoundException;
import com.arilsondev.publictaxmanagement.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static ProductRequest productRequest;
    private static Product PRODUCT;
    public static String PRODUCT_NAME;
    public static String PRODUCT_BRAND;
    public static BigDecimal PRODUCT_PRICE;
    public static String UNIT_MEASUREMENT;
    public static Integer OFFSET;
    public static Integer LIMIT;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Page<Product> products;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeAll
    static void initialize() {
        productRequest = toProductRequest();
        PRODUCT = toProduct();
        PRODUCT_NAME = "productName";
        PRODUCT_BRAND = "productBrand";
        PRODUCT_PRICE = new BigDecimal(10);
        UNIT_MEASUREMENT = "Kg";
        OFFSET = 0;
        LIMIT = 10;
    }

    @DisplayName("Should Post Product Successfully")
    @Test
    void postProductSuccessfully() {

        when(productRepository.save(productRequest.productRequestToProduct()))
                .thenReturn(PRODUCT);

        assertDoesNotThrow(() -> productService.postProduct(productRequest));
        assertEquals(PRODUCT.getProductName(), productRequest.getProductName());
        assertEquals(PRODUCT.getProductBrand(), productRequest.getProductBrand());
        assertEquals(PRODUCT.getProductPrice(), productRequest.getProductPrice());
    }

    @DisplayName("Should Get All Products Successfully")
    @Test
    void getAllProductsSuccessfully() {
        Pageable pageRequest = PageRequest.of(OFFSET, LIMIT, Sort.by("id"));

        when(productRepository.findAllWithFilters(PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, UNIT_MEASUREMENT, pageRequest))
                .thenReturn(products);

        assertDoesNotThrow(() -> productService.getAllProducts(PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, UNIT_MEASUREMENT, OFFSET, LIMIT));
    }

    @DisplayName("Get All Products Should throw Object Not Found Exception")
    @Test
    void getProductByIdThrowsObjectNotFoundException() {

        when(productRepository.findById(PRODUCT.getId()))
                .thenReturn(java.util.Optional.ofNullable(null));

        assertThrows(ObjectNotFoundException.class, () -> productService.getProductById(1L));
    }

    @DisplayName("Should Put Product Successfully")
    @Test
    void putProductSuccessfully() {

        when(productRepository.findById(PRODUCT.getId()))
                .thenReturn(java.util.Optional.ofNullable(PRODUCT));
        when(productRepository.save(PRODUCT))
                .thenReturn(PRODUCT);

        assertDoesNotThrow(() -> productService.putProduct(productRequest, PRODUCT.getId()));
        assertEquals(PRODUCT.getProductName(), productRequest.getProductName());
        assertEquals(PRODUCT.getProductBrand(), productRequest.getProductBrand());
        assertEquals(PRODUCT.getProductPrice(), productRequest.getProductPrice());
    }

    @Test
    void deleteProductSuccessfully() {

        when(productRepository.findById(PRODUCT.getId()))
                .thenReturn(java.util.Optional.ofNullable(PRODUCT));
        doNothing().when(productRepository).delete(PRODUCT);

        assertDoesNotThrow(() -> productService.deleteProduct(PRODUCT.getId()));
    }

    private static ProductRequest toProductRequest() {
        return ProductRequest.builder()
                .productName("productName")
                .productBrand("productBrand")
                .productPrice(new BigDecimal(10))
                .unitMeasurement("unitMeasurement")
                .build();
    }

    private static Product toProduct() {
        return Product.builder()
                .id(1L)
                .productName("productName")
                .productBrand("productBrand")
                .productPrice(new BigDecimal(10))
                .unitMeasurement("unitMeasurement")
                .build();
    }

}