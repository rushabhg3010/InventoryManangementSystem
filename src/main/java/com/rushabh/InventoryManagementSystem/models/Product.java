package com.rushabh.InventoryManagementSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "products")
@Data
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Column(unique = true,name = "product_sku")
    @NotBlank(message = "sku is required")
    private String productSku;

    @Positive(message = "product price must be a positive value")
    private BigDecimal productPrice;

    @Min(value=0, message = "stock quantity cannot be negative")
    private Integer stockQuantity;

    private String productDescription;

    private LocalDateTime expiryDate;

    private String imageUrl;

    private LocalDateTime createdDate =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productSku='" + productSku + '\'' +
                ", productPrice=" + productPrice +
                ", stockQuantity=" + stockQuantity +
                ", productDescription='" + productDescription + '\'' +
                ", expiryDate=" + expiryDate +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
