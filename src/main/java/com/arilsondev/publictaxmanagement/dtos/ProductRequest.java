package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Field productName is required")
    private String productName;
    @NotBlank(message = "Field productBrand is required")
    private String productBrand;
    @NotBlank
    @NotBlank(message = "Field productPrice is required")
    private BigDecimal productPrice;
    private LocalDateTime date = LocalDateTime.now();

    public Product productRequestToProduct() {
        return Product.builder()
                .productName(this.productName)
                .productBrand(this.productBrand)
                .productPrice(this.productPrice)
                .date(this.date)
                .build();
    }
}
