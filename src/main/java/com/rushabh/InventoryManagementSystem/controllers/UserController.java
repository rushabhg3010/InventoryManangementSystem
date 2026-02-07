package com.rushabh.InventoryManagementSystem.controllers;

import com.rushabh.InventoryManagementSystem.dtos.Response;
import com.rushabh.InventoryManagementSystem.dtos.UserDTO;
import com.rushabh.InventoryManagementSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping ("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById( @PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteUser(@RequestBody Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Response> getUserAndTransactions(@RequestBody Long id){
        return ResponseEntity.ok(userService.getUserTransactions(id));
    }
}
