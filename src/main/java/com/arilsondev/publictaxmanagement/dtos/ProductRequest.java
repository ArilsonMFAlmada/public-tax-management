package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @NotBlank
    private LocalDate date;

    public Product productRequestToProduct() {
        return Product.builder()
                .productName(this.productName)
                .productBrand(this.productBrand)
                .productPrice(this.productPrice)
                .date(this.date)
                .build();
    }
}
