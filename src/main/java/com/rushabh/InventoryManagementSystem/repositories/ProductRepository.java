package com.rushabh.InventoryManagementSystem.repositories;

import com.rushabh.InventoryManagementSystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingOrProductDescriptionContaining(String name, String description);

}
