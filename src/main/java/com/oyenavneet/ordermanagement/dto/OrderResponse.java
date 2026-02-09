package com.oyenavneet.ordermanagement.dto;

import com.oyenavneet.ordermanagement.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String customerName;
    private Double amount;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
