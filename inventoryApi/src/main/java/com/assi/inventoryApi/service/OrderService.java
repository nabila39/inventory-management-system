package com.assi.inventoryApi.service;

import com.assi.inventoryApi.model.dto.CustDto;
import com.assi.inventoryApi.model.dto.OrderDto;
import com.assi.inventoryApi.model.entity.Customer;
import com.assi.inventoryApi.model.entity.Order;
import com.assi.inventoryApi.model.entity.orderDetails;
import com.assi.inventoryApi.repository.CustRepo;
import com.assi.inventoryApi.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustRepo cstRepo;
    public List<OrderDto> getAllOrderss() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream()
                .map(OrderDto::OrderTOdto)
                .collect(Collectors.toList());
    }
    public OrderDto getOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepo.findById(id);
        return orderOptional.map(OrderDto::OrderTOdto).orElse(null);
    }
    public List<OrderDto> getCustOrdersById(Integer id) {
        List<Order> orders = orderRepo.getCustOrdersById(id);
        return orders.stream()
                .map(OrderDto::OrderTOdto)
                .collect(Collectors.toList());
    }

    public OrderDto addNewOrder(@RequestBody OrderDto order) {
        if (order.getCustomerId() != null) {
            Optional<Customer> customerOptional = cstRepo.findById(order.getCustomerId());
            if (customerOptional.isPresent()) {
                Order newOrder = orderRepo.save(Order.toEntity(order));
                return OrderDto.OrderTOdto(newOrder);
            }
        }
        return null;
    }
    public void deleteOrderById(Integer id) {
        this.orderRepo.deleteById(id);

    }
    public OrderDto updateFully(OrderDto order,Integer id){
        Optional<Order> getOrder= this.orderRepo.findById(id);
        if(getOrder.isPresent()){
            Order storeOrder=getOrder.get();
            storeOrder.setStatus(order.getStatus());
            storeOrder.setTotalPrice(order.getTotalPrice());
            Customer customer = cstRepo.findById(order.getCustomerId()).orElse(null);
            if (customer != null) {
                storeOrder.setCust(customer);
            }
            Order newOrder=orderRepo.save(storeOrder);
            return OrderDto.OrderTOdto(newOrder);
        }
        return null;
    }
    public OrderDto UpdatePartialy(Integer id, Map<String, Object> updates) {
        Optional<Order> Orderptionl = orderRepo.findById(id);
        if (Orderptionl.isPresent()) {//true
            Order order = Orderptionl.get();
            if (updates.containsKey("status")) {
                order.setStatus((String) updates.get("status"));
            }
            if (updates.containsKey("totalPrice")) {
                Double total = null;
                try {
                    total = Double.parseDouble(updates.get("totalPrice").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                order.setTotalPrice(total);
            }
            if (updates.containsKey("cId")) {
                Integer customerId = null;
                try {
                    customerId = Integer.parseInt(updates.get("cId").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                Customer customer = cstRepo.findById(customerId).orElse(null);
                if (customer != null) {
                    order.setCust(customer);
                }
            }
            Order newOrder = orderRepo.save(order);
            return OrderDto.OrderTOdto(newOrder);
        }
        return null;
    }
}
