package com.br.api_controle_estoque.controller;

import com.br.api_controle_estoque.model.User;
import com.br.api_controle_estoque.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userService.saveUser(user);

        // Build the destination URI for the newly creates user. To return the HTTP status code 201(created)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id){
        User findUser = userService.searchUser(id);

        if (findUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @Valid @RequestBody User user){
        User existingUser = userService.searchUser(id);

        if (existingUser != null){
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setProfile(user.getProfile());
            existingUser.setProducts(user.getProducts());
            existingUser.setStockMovements(user.getStockMovements());
            return ResponseEntity.ok(userService.saveUser(existingUser));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        User existingUser = userService.searchUser(id);
        if (existingUser != null){
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
