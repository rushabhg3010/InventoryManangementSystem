package com.rushabh.InventoryManagementSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rushabh.InventoryManagementSystem.enums.TransactionStatus;
import com.rushabh.InventoryManagementSystem.enums.TransactionType;
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

public class TransactionDTO {

    private Long id;

    private Integer totalProducts;
    private BigDecimal totalPrice;

    private TransactionType transactionType;// purchase, sale, return to supplier

    private TransactionStatus transactionStatus; // can be pending, completed, processing, done

    private String description;
    private String notes;
    private LocalDateTime transactionDate;
    private LocalDateTime updateDate;

    private ProductDTO product;


    private UserDTO user;

    private VendorDTO vendor;

}
