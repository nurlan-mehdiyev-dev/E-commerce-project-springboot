// Cart DTO
package com.example.productsShopping.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private String productModel;
    private Integer quantity;
}