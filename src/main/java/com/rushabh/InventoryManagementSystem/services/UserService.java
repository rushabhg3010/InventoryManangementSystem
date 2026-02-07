package com.rushabh.InventoryManagementSystem.services;

import com.rushabh.InventoryManagementSystem.dtos.LoginRequest;
import com.rushabh.InventoryManagementSystem.dtos.RegisterRequest;
import com.rushabh.InventoryManagementSystem.dtos.Response;
import com.rushabh.InventoryManagementSystem.dtos.UserDTO;
import com.rushabh.InventoryManagementSystem.models.User;

public interface UserService {

    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser (Long id , UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);

}
