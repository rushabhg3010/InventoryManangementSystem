package com.rushabh.InventoryManagementSystem.repositories;

import com.rushabh.InventoryManagementSystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
