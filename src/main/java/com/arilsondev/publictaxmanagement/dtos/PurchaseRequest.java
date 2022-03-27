package com.arilsondev.publictaxmanagement.dtos;

import com.arilsondev.publictaxmanagement.domains.Product;
import com.arilsondev.publictaxmanagement.domains.Purchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static com.arilsondev.publictaxmanagement.interfaces.Messages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {

    @NotNull(message = PRODUCT_ID_VALIDATION)
    private List<Long> productsIds;

    @NotBlank(message = PURCHASE_DATE)
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate date;

    @NotBlank(message = PURCHASE_CEP)
    private String cep;

    @NotBlank(message = PURCHASE_ESTATE)
    @Size(min = 1, max = 2)
    private String estate;

    @NotBlank(message = PURCHASE_CITY)
    @Size(min = 1, max = 200)
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
