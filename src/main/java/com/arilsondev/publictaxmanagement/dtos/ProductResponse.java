package com.arilsondev.publictaxmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productName;

    private String productBrand;

    private BigDecimal productPrice;

    private String unitMeasurement;
}
