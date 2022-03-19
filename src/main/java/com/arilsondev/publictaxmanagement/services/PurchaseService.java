package com.arilsondev.publictaxmanagement.services;

import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface PurchaseService {

    PurchaseResponse postPurchase(PurchaseRequest purchaseRequest);

    void deletePurchase(Long purchaseId);

    PurchaseResponse putPurchase(PurchaseRequest purchaseRequest, Long purchaseId);

    PurchaseResponse getPurchaseById(Long purchaseId);

    Page<PurchaseResponse> getAllPurchases(LocalDate date, String cep, String city, String estate, Integer offset, Integer limit);
}
