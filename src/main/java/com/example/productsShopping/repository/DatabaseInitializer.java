package com.example.productsShopping.repository;

import com.example.productsShopping.entity.Product;
import com.example.productsShopping.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Проверяем, есть ли данные в таблице Products
        if (productRepository.count() == 0) {
            User user = createUser("timur", "mammedov", "timur@example.com", "timur", "timur");

            // Создаем список продуктов
            List<Product> products = Arrays.asList(
                    createProduct("Product1", "ModelA", "Category1", "Description of Product1", 100.0, 5, "https://example.com/product1.jpg", user),
                    createProduct("Product2", "ModelB", "Category2", "Description of Product2", 150.0, 5, "https://example.com/product2.jpg", user),
                    createProduct("Product3", "ModelC", "Category3", "Description of Product3", 200.0, 4, "https://example.com/product3.jpg", user),
                    createProduct("Product4", "ModelD", "Category4", "Description of Product4", 250.0, 4, "https://example.com/product4.jpg", user),
                    createProduct("Product5", "ModelE", "Category5", "Description of Product5", 300.0, 3, "https://example.com/product5.jpg", user)
            );

            // Сохраняем все продукты в базе данных
            productRepository.saveAll(products);

            System.out.println("База данных успешно заполнена начальными данными.");
        } else {
            System.out.println("База данных уже содержит данные. Инициализация пропущена.");
        }
    }

    private User createUser(String name, String surname, String email, String username, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    private Product createProduct(String brand, String model, String category, String description, double price, int rate, String imageUrl, User user) {
        Product product = new Product();
        product.setBrand(brand);
        product.setModel(model);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setRate(rate);
        product.setImageUrl(imageUrl);
        product.setUser(user);
        return product;
    }
}
