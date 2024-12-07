package com.github.supercodingspringproject.web.dto;

import com.github.supercodingspringproject.repository.orders.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderInfo {
    private Integer orderId;
    private Integer modelId;
    private Integer sneakerSize;
    private Integer orderQuantity;
    private Double totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime orderAt;
}
