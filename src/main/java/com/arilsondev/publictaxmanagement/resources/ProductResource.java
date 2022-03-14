package com.arilsondev.publictaxmanagement.resources;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import com.arilsondev.publictaxmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductResource {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> postProduct(@Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<ProductResponse>(productService.postProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return new ResponseEntity<List<ProductResponse>>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {

        return new ResponseEntity<ProductResponse>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long productId) {

        return new ResponseEntity<ProductResponse>(productService.putProduct(productRequest, productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
