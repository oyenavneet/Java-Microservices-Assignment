package com.oyenavneet.ordermanagement.service;

import com.oyenavneet.ordermanagement.dto.OrderRequest;
import com.oyenavneet.ordermanagement.dto.OrderResponse;
import com.oyenavneet.ordermanagement.model.Order;
import com.oyenavneet.ordermanagement.model.OrderStatus;
import com.oyenavneet.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_ShouldReturnOrderResponse_WhenValidRequest() {
        OrderRequest request = new OrderRequest("John Doe", 100.0);
        Order savedOrder = Order.builder()
                .orderId("123")
                .customerName("John Doe")
                .amount(100.0)
                .status(OrderStatus.NEW)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponse response = orderService.createOrder(request);

        assertNotNull(response);
        assertEquals("123", response.getOrderId());
        assertEquals("John Doe", response.getCustomerName());
        assertEquals(100.0, response.getAmount());
        assertEquals(OrderStatus.NEW, response.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
