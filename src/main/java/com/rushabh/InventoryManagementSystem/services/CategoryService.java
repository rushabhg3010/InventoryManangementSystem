package com.rushabh.InventoryManagementSystem.services;

import com.rushabh.InventoryManagementSystem.dtos.CategoryDTO;
import com.rushabh.InventoryManagementSystem.dtos.Response;

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories();

    Response getCategoryById(Long id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);
}
