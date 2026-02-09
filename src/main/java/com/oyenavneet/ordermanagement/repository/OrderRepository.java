package com.oyenavneet.ordermanagement.repository;

import com.oyenavneet.ordermanagement.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private final ConcurrentHashMap<String, Order> orderStore = new ConcurrentHashMap<>();

    public Order save(Order order) {
        orderStore.put(order.getOrderId(), order);
        return order;
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderStore.values());
    }
}
