package com.arilsondev.publictaxmanagement.domains;

import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product")
public class Product extends AuditDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 80)
    private String productName;

    @Column(name = "product_brand", nullable = false, length = 80)
    private String productBrand;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "unit_measurement", nullable = false)
    private String unitMeasurement;

    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
                .productName(this.productName)
                .productBrand(this.productBrand)
                .productPrice(this.productPrice)
                .unitMeasurement(this.unitMeasurement)
                .build();
    }
}
