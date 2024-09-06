package com.aljis.order_service.controller;

import com.aljis.order_service.dto.OrderRequest;
import com.aljis.order_service.dto.OrderResponse;
import com.aljis.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> allOrders = orderService.getAllOrders();

        return ResponseEntity.ok(allOrders);
    }
}