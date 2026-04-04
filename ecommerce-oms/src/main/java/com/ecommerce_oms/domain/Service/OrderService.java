package com.ecommerce_oms.domain.Service;


import com.ecommerce_oms.domain.entity.Order;
import com.ecommerce_oms.domain.entity.User;
import com.ecommerce_oms.domain.repository.OrderRepository;
import com.ecommerce_oms.domain.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class OrderService {
    private InventoryService inventoryService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public OrderService(InventoryService inventoryService, UserRepository userRepository, OrderRepository orderRepository) {
        this.inventoryService = inventoryService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found" + userId));
        Order order = new Order();


        order.setUser(user);
        order.setStatus("CREATED");
        order.setTotalAmount(BigDecimal.ZERO);
        order.setOrderNumber(UUID.randomUUID().toString());

        orderRepository.save(order);

    }



}