package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import com.arilsondev.publictaxmanagement.exceptions.ObjectNotFoundException;
import com.arilsondev.publictaxmanagement.repositories.PurchaseRepository;
import com.arilsondev.publictaxmanagement.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public PurchaseResponse postPurchase(PurchaseRequest purchaseRequest) {
        Purchase purchase = purchaseRepository.save(purchaseRequest.purchaseRequestToPurchase());

        return purchase.toPurchaseResponse();
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        purchaseRepository.delete(purchase);
    }

    @Override
    public PurchaseResponse putPurchase(PurchaseRequest purchaseRequest, Long purchaseId) {
        return null;
    }

    @Override
    public PurchaseResponse getPurchaseById(Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        return purchase.toPurchaseResponse();
    }

    @Override
    public Page<PurchaseResponse> getAllPurchases(LocalDate date, String cep, String city, String estate, Integer offset, Integer limit) {
        return null;
    }

    private Purchase getPurchase(Long purchaseId) {
        return purchaseRepository.findById(purchaseId).orElseThrow(() -> new ObjectNotFoundException("Purchase not found! ProductId: " + purchaseId + ", Type: " + Purchase.class.getSimpleName()));
    }
}
