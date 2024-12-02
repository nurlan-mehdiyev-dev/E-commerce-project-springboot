
// Cart Controller
package com.example.productsShopping.controller;

import com.example.productsShopping.dto.CartItemDto;
import com.example.productsShopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<CartItemDto> addToCart(
            Principal principal,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer quantity
    ) {
        return ResponseEntity.ok(cartService.addToCart(principal.getName(), productId, quantity));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeFromCart(
            Principal principal,
            @PathVariable Long productId
    ) {
        cartService.removeFromCart(principal.getName(), productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateCartItemQuantity(
            Principal principal,
            @PathVariable Long productId,
            @RequestParam Integer quantity
    ) {
        CartItemDto updatedItem = cartService.updateCartItemQuantity(principal.getName(), productId, quantity);
        return updatedItem != null
                ? ResponseEntity.ok(updatedItem)
                : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getUserCart(Principal principal) {
        return ResponseEntity.ok(cartService.getUserCart(principal.getName()));
    }
}