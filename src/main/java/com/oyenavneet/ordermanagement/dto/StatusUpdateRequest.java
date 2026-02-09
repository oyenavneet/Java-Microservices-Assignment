package com.oyenavneet.ordermanagement.dto;

import com.oyenavneet.ordermanagement.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {
    private OrderStatus status;
}
