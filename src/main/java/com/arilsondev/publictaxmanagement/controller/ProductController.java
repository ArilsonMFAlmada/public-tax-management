package com.arilsondev.publictaxmanagement.controller;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import com.arilsondev.publictaxmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController implements BaseController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> postProduct(
            @Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(productService.postProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            String productName,
            String productBrand,
            BigDecimal productPrice,
            String unitMeasurement,
            @RequestParam("_offset") @PositiveOrZero @NotNull Integer offset,
            @RequestParam("_limit") @PositiveOrZero @NotNull @Max(ACCEPT_RANGE) Integer limit) {

        return partialContent(productService.getAllProducts(productName, productBrand, productPrice, unitMeasurement, offset, limit), ACCEPT_RANGE);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {

        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @Valid @RequestBody ProductRequest productRequest,
            @PathVariable Long productId) {

        return new ResponseEntity<>(productService.putProduct(productRequest, productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
