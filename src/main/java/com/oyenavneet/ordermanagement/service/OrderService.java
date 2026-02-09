package com.oyenavneet.ordermanagement.service;

import com.oyenavneet.ordermanagement.dto.OrderRequest;
import com.oyenavneet.ordermanagement.dto.OrderResponse;
import com.oyenavneet.ordermanagement.exception.OrderNotFoundException;
import com.oyenavneet.ordermanagement.model.Order;
import com.oyenavneet.ordermanagement.model.OrderStatus;
import com.oyenavneet.ordermanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .customerName(request.getCustomerName())
                .amount(request.getAmount())
                .status(OrderStatus.NEW)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    public OrderResponse getOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        return mapToResponse(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        validateStatusTransition(order.getStatus(), newStatus);

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(updatedOrder);
    }

    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        if (currentStatus == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot change status of a COMPLETED order");
        }

        if (currentStatus == OrderStatus.NEW && newStatus != OrderStatus.PROCESSING) {
            throw new IllegalArgumentException("NEW orders can only move to PROCESSING");
        }

        if (currentStatus == OrderStatus.PROCESSING && newStatus != OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("PROCESSING orders can only move to COMPLETED");
        }
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .customerName(order.getCustomerName())
                .amount(order.getAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
