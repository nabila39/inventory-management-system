package com.assi.inventoryApi.model.entity;

import com.assi.inventoryApi.model.dto.OrderDetailsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orderDetails")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderDetailsId.class)
public class orderDetails {
    @Id
    private Integer orderId;
    @Id
    private Integer pId;

    @ManyToOne
    @JoinColumn(name = "pId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order order;

    private Double totalPrice;
    private Integer quantity;
    private Double price;

    public static orderDetails toEntity(OrderDetailsDto dto){
        return orderDetails.builder()
                .orderId(dto.getOrderId())
                .pId(dto.getPId())
                .quantity(dto.getQuantity())
                .totalPrice(dto.getTotalPrice())
                .price(dto.getPrice())
                .build();
    }
}
