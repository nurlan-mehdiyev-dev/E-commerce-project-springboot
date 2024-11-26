package com.example.productsShopping.service;


import com.example.productsShopping.dto.ProductDto;
import com.example.productsShopping.entity.Product;
import com.example.productsShopping.entity.User;
import com.example.productsShopping.repository.ProductRepository;
import com.example.productsShopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductDto addProduct(String username, ProductDto productDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setModel(productDto.getModel());
        product.setUser(user);

        Product savedProduct = productRepository.save(product);

        return mapToDto(savedProduct);
    }

    public List<ProductDto> getUserProducts(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getProducts().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(String username, Long productId) {
        Product product = productRepository.findByIdAndUser_Username(productId, username)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setModel(product.getModel());
        return dto;
    }

    @Transactional
    public ProductDto updateProduct(String username, Long productId, ProductDto productDto) {
        // Найдем компьютер, принадлежащий конкретному пользователю
        Product product = productRepository.findByIdAndUser_Username(productId, username)
                .orElseThrow(() -> new RuntimeException("Product not found or access denied"));

        // Обновим поля компьютера
        product.setName(productDto.getName());
        product.setModel(productDto.getModel());

        // Сохраним обновленный компьютер
        Product updatedProduct = productRepository.save(product);

        // Преобразуем в DTO и вернем
        return mapToDto(updatedProduct);
    }
}