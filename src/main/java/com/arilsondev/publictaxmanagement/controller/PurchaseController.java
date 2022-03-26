package com.arilsondev.publictaxmanagement.controller;

import com.arilsondev.publictaxmanagement.dtos.PurchaseRequest;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import com.arilsondev.publictaxmanagement.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("purchases")
public class PurchaseController implements BaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> postPurchase(
            @Valid @RequestBody PurchaseRequest purchaseRequest) {

        return new ResponseEntity<>(purchaseService.postPurchase(purchaseRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases(
            LocalDate date,
            String cep,
            String city,
            String estate,
            @RequestParam("_offset") @PositiveOrZero @NotNull Integer offset,
            @RequestParam("_limit") @PositiveOrZero @NotNull @Max(ACCEPT_RANGE) Integer limit) {

        return partialContent(purchaseService.getAllPurchases(date, cep, city, estate, offset, limit), ACCEPT_RANGE);
    }

    @GetMapping(value = "/{purchaseId}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(
            @PathVariable Long purchaseId) {

        return new ResponseEntity<>(purchaseService.getPurchaseById(purchaseId), HttpStatus.OK);
    }

    @PutMapping(value = "/{purchaseId}")
    public ResponseEntity<PurchaseResponse> updatePurchase(
            @Valid @RequestBody PurchaseRequest purchaseRequest,
            @PathVariable Long purchaseId) {

        return new ResponseEntity<>(purchaseService.putPurchase(purchaseRequest, purchaseId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{purchaseId}")
    public ResponseEntity<Void> deletePurchase(
            @PathVariable Long purchaseId) {

        purchaseService.deletePurchase(purchaseId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
