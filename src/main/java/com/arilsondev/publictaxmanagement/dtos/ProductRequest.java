package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank
    private String productName;

    @NotBlank
    private String productBrand;

    @NotBlank
    private BigDecimal productPrice;

    @NotBlank
    private String unitMeasurement;

    public Product productRequestToProduct() {
        return Product.builder()
                .productName(this.productName)
                .productBrand(this.productBrand)
                .productPrice(this.productPrice)
                .unitMeasurement(this.unitMeasurement)
                .build();
    }
}
