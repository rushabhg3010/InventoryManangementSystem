package com.rushabh.InventoryManagementSystem.models;

import com.rushabh.InventoryManagementSystem.enums.TransactionStatus;
import com.rushabh.InventoryManagementSystem.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "transactions")
@Data
@Builder

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalProducts;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;// purchase, sale, return to supplier

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus; // can be pending, completed, processing, done

    private String description;
    private String notes;
    private final LocalDateTime transactionDate = LocalDateTime.now();
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", transactionDate=" + transactionDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
