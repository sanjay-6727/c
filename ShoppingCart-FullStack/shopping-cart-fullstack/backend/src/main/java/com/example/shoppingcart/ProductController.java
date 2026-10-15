package com.example.shoppingcart;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                new Product("Wireless Headphones", 1499, "Comfortable headphones"),
                new Product("Smart Watch", 2299, "Everyday fitness and notifications"),
                new Product("Backpack", 899, "Daily-use backpack"),
                new Product("Water Bottle", 299, "Reusable steel bottle")
            ));
        }
        return repository.findAll();
    }
}