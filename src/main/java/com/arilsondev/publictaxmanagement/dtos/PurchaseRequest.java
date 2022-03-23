package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.arilsondev.publictaxmanagement.repositories.ProductRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    @NotNull
    private List<Long> productsIds;

    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate date;

    @NotBlank
    private String cep;

    @NotBlank
    private String estate;

    @NotBlank
    private String city;

    public Purchase purchaseRequestToPurchase(List<Product> products) {
        return Purchase.builder()
                .products(products)
                .date(this.date)
                .cep(this.cep)
                .estate(this.estate)
                .city(this.city)
                .build();
    }
}
