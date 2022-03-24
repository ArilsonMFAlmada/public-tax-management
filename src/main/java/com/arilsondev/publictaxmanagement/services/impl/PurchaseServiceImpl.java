package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import com.arilsondev.publictaxmanagement.exceptions.ObjectNotFoundException;
import com.arilsondev.publictaxmanagement.repositories.PurchaseRepository;
import com.arilsondev.publictaxmanagement.services.ProductService;
import com.arilsondev.publictaxmanagement.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;

    @Transactional
    @Override
    public PurchaseResponse postPurchase(PurchaseRequest purchaseRequest) {

        List<Product> products = verifyProducts(purchaseRequest);

        Purchase purchase = purchaseRepository.save(purchaseRequest.purchaseRequestToPurchase(products));

        return purchase.toPurchaseResponse();
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        purchaseRepository.delete(purchase);
    }

    @Transactional
    @Override
    public PurchaseResponse putPurchase(PurchaseRequest purchaseRequest, Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        List<Product> products = verifyProducts(purchaseRequest);

        purchase.setId(purchaseId);
        purchase.setProducts(products);
        purchase.setCep(purchaseRequest.getCep());
        purchase.setEstate(purchaseRequest.getEstate());
        purchase.setCity(purchaseRequest.getCity());
        purchase.setDate(purchaseRequest.getDate());

        Purchase purchaseTobeSaved = purchaseRepository.save(purchase);

        return purchaseTobeSaved.toPurchaseResponse();
    }

    @Override
    public PurchaseResponse getPurchaseById(Long purchaseId) {
        Purchase purchase = getPurchase(purchaseId);

        return purchase.toPurchaseResponse();
    }

    @Override
    public Page<PurchaseResponse> getAllPurchases(LocalDate date,
                                                  String cep,
                                                  String city,
                                                  String estate,
                                                  Integer offset,
                                                  Integer limit) {
        Pageable pageRequest = PageRequest.of(offset, limit, Sort.by("id"));

        return purchaseRepository.findAllWithFilters(
                isNull(date) ? null : date,
                isNull(cep) ? null : cep,
                isNull(city) ? null : city,
                isNull(estate) ? null : estate,
                pageRequest
        );
    }

    private Purchase getPurchase(Long purchaseId) {

        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ObjectNotFoundException("Purchase not found! PurchaseId: " + purchaseId + ", Type: " + Purchase.class.getSimpleName()));
    }

    private List<Product> verifyProducts(PurchaseRequest purchaseRequest) {

        return purchaseRequest.getProductsIds()
                .stream()
                .map(productService::getProduct)
                .collect(Collectors.toList());
    }
}
