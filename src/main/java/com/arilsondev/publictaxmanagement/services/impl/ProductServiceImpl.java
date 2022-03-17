package com.arilsondev.publictaxmanagement.services.impl;

import com.arilsondev.publictaxmanagement.dtos.ProductRequest;
import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.exceptions.ObjectNotFoundException;
import com.arilsondev.publictaxmanagement.repositories.ProductRepository;
import com.arilsondev.publictaxmanagement.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse postProduct(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.productRequestToProduct());

        return product.toProductResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts() {

       return productRepository.findAll()
               .stream()
               .map(Product::toProductResponse).collect(Collectors.toList());
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
        product.setDate(productRequest.getDate());

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
