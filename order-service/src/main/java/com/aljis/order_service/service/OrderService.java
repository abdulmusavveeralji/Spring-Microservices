package com.aljis.order_service.service;

import com.aljis.order_service.client.InventoryClient;
import com.aljis.order_service.dto.OrderRequest;
import com.aljis.order_service.dto.OrderResponse;
import com.aljis.order_service.event.OrderPlacedEvent;
import com.aljis.order_service.model.Order;
import com.aljis.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(OrderRequest orderRequest) {

        boolean inStock = inventoryClient.isInStock(orderRequest.getSkuCode(), orderRequest.getQuantity());

        if (inStock) {
            Order order = new Order();
            order.setOrderNumber(String.valueOf(UUID.randomUUID()));
            order.setPrice(orderRequest.getPrice());
            order.setQuantity(orderRequest.getQuantity());
            order.setSkuCode(orderRequest.getSkuCode());
            order.setEmail(orderRequest.getUserDetails().getEmail());
            orderRepository.save(order);
            log.info("Order saved {}", order.getSkuCode());

            // send a message to kafka topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();

            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(order.getEmail());
            orderPlacedEvent.setFirstName("Alji");
            orderPlacedEvent.setLastName("Abdul");
            log.info("start - Sending Order Placed Event {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("end - Sending Order Placed Event {} to kafka topic order-placed", orderPlacedEvent);
        } else {
            throw new RuntimeException("Product is out of stock");
        }
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(m -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderNumber(m.getOrderNumber());
            orderResponse.setId(m.getId());
            orderResponse.setPrice(m.getPrice());
            orderResponse.setQuantity(m.getQuantity());
            orderResponse.setSkuCode(m.getSkuCode());
            return orderResponse;
        }).collect(Collectors.toList());
    }
}