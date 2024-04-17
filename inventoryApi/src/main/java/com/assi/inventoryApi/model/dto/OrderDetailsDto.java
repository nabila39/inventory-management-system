package com.assi.inventoryApi.model.dto;

import com.assi.inventoryApi.model.entity.Stock;
import com.assi.inventoryApi.model.entity.orderDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private Integer orderId;
    private Integer pId;
    private Double totalPrice;
    private Integer quantity;
    private Double price;

    public static OrderDetailsDto orderDetailsDto(orderDetails orderDetails){
        return OrderDetailsDto.builder().
                orderId(orderDetails.getOrderId()).
                pId(orderDetails.getPId()).
                quantity(orderDetails.getQuantity()).
                totalPrice(orderDetails.getTotalPrice()).
                price(orderDetails.getPrice()).
                build();

    }
}
