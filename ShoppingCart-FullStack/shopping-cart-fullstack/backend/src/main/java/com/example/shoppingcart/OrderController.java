package com.example.shoppingcart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        if (order.getCustomerName() == null || order.getCustomerName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer name is required");
        }
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

        double total = 0;
        for (OrderItem item : order.getItems()) {
            if (item.getProductId() == null || item.getQuantity() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order item");
            }
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Product not found"));
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            total += product.getPrice() * item.getQuantity();
        }

        order.setTotal(total);
        order.setCreatedAt(Instant.now().toString());
        return orderRepository.save(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}