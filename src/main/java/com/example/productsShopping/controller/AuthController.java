package com.example.productsShopping.controller;


import com.example.productsShopping.dto.LoginRequest;
import com.example.productsShopping.dto.RegisterRequest;
import com.example.productsShopping.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")  // Allow CORS for this controller
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }
}

