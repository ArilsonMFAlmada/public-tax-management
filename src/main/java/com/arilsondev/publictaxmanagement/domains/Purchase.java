package com.arilsondev.publictaxmanagement.domains;

import com.arilsondev.publictaxmanagement.dtos.ProductResponse;
import com.arilsondev.publictaxmanagement.dtos.PurchaseResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "purchase")
public class Purchase extends AuditDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "estate", nullable = false)
    private String estate;

    @Column(name = "city", nullable = false)
    private String city;

    public PurchaseResponse toPurchaseResponse() {
        return PurchaseResponse.builder()
                .productsNames(this.products.stream()
                        .map(product -> product.getProductName())
                        .collect(Collectors.toList()))
                .date(this.date)
                .cep(this.cep)
                .estate(this.estate)
                .city(this.city)
                .build();
    }
}
