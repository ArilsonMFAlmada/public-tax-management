package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.repositories.PurchaseRepository;
import com.arilsondev.publictaxmanagement.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    private static PurchaseRequest purchaseRequest;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeAll
    void initialize() {
        purchaseRequest = toPurchaseRequest();
    }

    @Test
    void postPurchaseSuccessfully() {
    }

    @Test
    void deletePurchaseSuccessfully() {
    }

    @Test
    void putPurchaseSuccessfully() {
    }

    @Test
    void getPurchaseByIdSuccessfully() {
    }

    @Test
    void getAllPurchasesSuccessfully() {
    }

    private PurchaseRequest toPurchaseRequest() {
        return PurchaseRequest.builder()
                .productsIds(List.of(1L))
                .date(LocalDate.now())
                .cep("36016520")
                .estate("MG")
                .city("Judge From Outside")
                .build();
    }
}