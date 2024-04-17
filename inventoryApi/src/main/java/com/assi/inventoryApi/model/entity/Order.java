package com.assi.inventoryApi.model.entity;
import com.assi.inventoryApi.model.dto.OrderDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Table(name = "Orders")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "cId", foreignKey = @ForeignKey(name = "cId"))
    private Customer cust;
    private Double totalPrice;
    private String status; //"Pending", "Shipped", "Delivered"

    public static Order toEntity(OrderDto orderDto) {
        Order order = Order.builder()
                .orderId(orderDto.getOrderId())
                .orderDate(orderDto.getOrderDate())
                .totalPrice(orderDto.getTotalPrice())
                .status(orderDto.getStatus())
                .build();
        if (orderDto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setCId(orderDto.getCustomerId());
            order.setCust(customer);
        }
        return order;
    }






}
