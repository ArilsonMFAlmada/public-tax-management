package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import static com.arilsondev.publictaxmanagement.interfaces.Messages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = PRODUCT_NAME_VALIDATION)
    @Size(min = 1, max = 200)
    private String productName;

    @NotBlank(message = PRODUCT_BRAND_VALIDATION)
    @Size(min = 1, max = 200)
    private String productBrand;

    @NotBlank(message = PRODUCT_PRICE_VALIDATION)
    @Positive
    private BigDecimal productPrice;

    @NotBlank(message = UNIT_MEASUREMENT_VALIDATION)
    @Size(min = 1, max = 10)
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
