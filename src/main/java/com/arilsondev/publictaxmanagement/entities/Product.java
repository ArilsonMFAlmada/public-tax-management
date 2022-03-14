package com.arilsondev.publictaxmanagement.entities;

import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 80)
    private String productName;

    @Column(name = "product_brand", nullable = false, length = 80)
    private String productBrand;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
                .productName(this.productName)
                .productBrand(this.productBrand)
                .productPrice(this.productPrice)
                .date(this.date)
                .build();
    }
}
