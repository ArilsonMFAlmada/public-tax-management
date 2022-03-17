package com.arilsondev.publictaxmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productName;
    private String productBrand;
    private BigDecimal productPrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
}
