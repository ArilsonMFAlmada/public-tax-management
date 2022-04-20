package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.repositories.PurchaseRepository;
import com.arilsondev.publictaxmanagement.services.ProductService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    private static PurchaseRequest purchaseRequest;
    private static Product PRODUCT;
    private static Purchase PURCHASE;
    private static LocalDate DATE;
    private static String CEP;
    private static String ESTATE;
    private static String CITY;
    public static Integer OFFSET;
    public static Integer LIMIT;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductService productService;

    @Mock
    private Page<Purchase> purchases;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeAll
    static void initialize() {
        purchaseRequest = toPurchaseRequest();
        PRODUCT = toProduct();
        PURCHASE = toPurchase();
        DATE = LocalDate.now();
        CEP = "36016520";
        ESTATE = "MG";
        CITY = "Judge From Outside";
        OFFSET = 0;
        LIMIT = 10;
    }

    @DisplayName("Should Post Purchase Successfully")
    @Test
    void postPurchaseSuccessfully() {

        when(productService.getProduct(1L))
                .thenReturn(PRODUCT);

        when(purchaseRepository.save(purchaseRequest.purchaseRequestToPurchase(List.of(PRODUCT))))
                .thenReturn(PURCHASE);

        assertDoesNotThrow(() -> purchaseService.postPurchase(purchaseRequest));
        assertEquals(List.of(PRODUCT.getId()), purchaseRequest.getProductsIds());
        assertEquals(PURCHASE.getDate(), purchaseRequest.getDate());
        assertEquals(PURCHASE.getCep(), purchaseRequest.getCep());
        assertEquals(PURCHASE.getEstate(), purchaseRequest.getEstate());
        assertEquals(PURCHASE.getCity(), purchaseRequest.getCity());
    }

    @DisplayName("Should Delete Purchase Successfully")
    @Test
    void deletePurchaseSuccessfully() {

        when(purchaseRepository.findById(PURCHASE.getId()))
                .thenReturn(java.util.Optional.ofNullable(PURCHASE));

        doNothing().when(purchaseRepository).delete(PURCHASE);

        assertDoesNotThrow(() -> purchaseService.deletePurchase(PURCHASE.getId()));
    }

    @DisplayName("Should Put Purchase Successfully")
    @Test
    void putPurchaseSuccessfully() {

        when(productService.getProduct(1L))
                .thenReturn(PRODUCT);

        when(purchaseRepository.findById(PURCHASE.getId()))
                .thenReturn(java.util.Optional.ofNullable(PURCHASE));

        when(purchaseRepository.save(PURCHASE))
                .thenReturn(PURCHASE);

        assertDoesNotThrow(() -> purchaseService.putPurchase(purchaseRequest, 1L));
        assertEquals(PURCHASE.getDate(), purchaseRequest.getDate());
        assertEquals(PURCHASE.getCep(), purchaseRequest.getCep());
        assertEquals(PURCHASE.getEstate(), purchaseRequest.getEstate());
        assertEquals(PURCHASE.getCity(), purchaseRequest.getCity());
    }

    @DisplayName("Should Get Purchase By Id Successfully")
    @Test
    void getPurchaseByIdSuccessfully() {

        when(purchaseRepository.findById(anyLong()))
                .thenReturn(Optional.of(PURCHASE));

        assertDoesNotThrow(() -> purchaseService.getPurchaseById(1L));
    }

    @DisplayName("Should Get All Purchases Successfully")
    @Test
    void getAllPurchasesSuccessfully() {

        Pageable pageRequest = PageRequest.of(OFFSET, LIMIT, Sort.by("id"));

        when(purchaseRepository.findAllWithFilters(DATE, CEP, CITY, ESTATE, pageRequest))
                .thenReturn(purchases);

        assertDoesNotThrow(() -> purchaseService.getAllPurchases(DATE, CEP, CITY, ESTATE, OFFSET, LIMIT));
    }

    private static PurchaseRequest toPurchaseRequest() {
        return PurchaseRequest.builder()
                .productsIds(List.of(1L))
                .date(DATE)
                .cep(CEP)
                .estate(ESTATE)
                .city(CITY)
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

    private static Purchase toPurchase() {
        return Purchase.builder()
                .id(1L)
                .products(List.of(PRODUCT))
                .date(DATE)
                .cep(CEP)
                .estate(ESTATE)
                .city(CITY)
                .build();
    }
}