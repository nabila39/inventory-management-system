package com.assi.inventoryApi.model.dto;

import com.assi.inventoryApi.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Date orderDate;
    private Integer customerId;
    private Double totalPrice;
    private String status; //"Pending", "Shipped", "Delivered"

    public static OrderDto OrderTOdto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .customerId(order.getCust().getCId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }

}
