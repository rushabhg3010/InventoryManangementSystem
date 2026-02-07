package com.rushabh.InventoryManagementSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductDTO {

    private Long id;
    private Long categoryId;
    private Long productId;
    private Long vendorId;

    private String productName;

    private String productSku;

    private BigDecimal productPrice;

    private Integer stockQuantity;

    private String productDescription;

    private LocalDateTime expiryDate;

    private String imageUrl;

    private LocalDateTime createdDate;


}
