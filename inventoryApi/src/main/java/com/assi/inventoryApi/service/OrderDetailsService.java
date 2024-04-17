package com.assi.inventoryApi.service;

import com.assi.inventoryApi.model.dto.OrderDetailsDto;
import com.assi.inventoryApi.model.dto.OrderDto;
import com.assi.inventoryApi.model.dto.StockDto;
import com.assi.inventoryApi.model.entity.*;
import com.assi.inventoryApi.repository.OrderRepo;
import com.assi.inventoryApi.repository.ProRepo;
import com.assi.inventoryApi.repository.orderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrderDetailsService {
    @Autowired
    private final orderDetailsRepo orderDetailsRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProRepo proRepo;

    public OrderDetailsService(orderDetailsRepo orderDetailsRepo) {
        this.orderDetailsRepo = orderDetailsRepo;
    }
    public List<OrderDetailsDto> getAllOrderDetails() {
        List<orderDetails> orderDetailsList = orderDetailsRepo.findAll();
        return orderDetailsList.stream()
                .map(OrderDetailsDto::orderDetailsDto)
                .collect(Collectors.toList());
    }
    public OrderDetailsDto getById(Integer oid, Integer pid) {
        Optional<orderDetails> order = orderDetailsRepo.findById(new OrderDetailsId(oid, pid));
        return order.map(OrderDetailsDto::orderDetailsDto).orElse(null);
    }
    public OrderDetailsDto addNewOrderDetails(@RequestBody OrderDetailsDto order) {
        if (order.getOrderId() != null && order.getPId() !=null) {
            Optional<Order> orderOptional = orderRepo.findById(order.getOrderId());
            Optional<Product> productOptional = proRepo.findById(order.getPId());
            if (orderOptional.isPresent() && productOptional.isPresent()) {
                orderDetails newOrder = orderDetailsRepo.save(orderDetails.toEntity(order));
                return OrderDetailsDto.orderDetailsDto(newOrder);
            }
        }
        return null;
    }
    public void deleteOrderDetailsById(Integer oid, Integer pid) {
        this.orderDetailsRepo.deleteById(new OrderDetailsId(oid, pid));

    }
    public OrderDetailsDto updateFully(OrderDetailsDto orderDetailsDto,Integer oid, Integer pid){
        Optional<orderDetails> getOrderDetails= orderDetailsRepo.findById(new OrderDetailsId(oid, pid));
        if(getOrderDetails.isPresent() ){
            orderDetails storeOrder=getOrderDetails.get();
            storeOrder.setTotalPrice(orderDetailsDto.getTotalPrice());
            storeOrder.setPrice(orderDetailsDto.getPrice());
            storeOrder.setQuantity(orderDetailsDto.getQuantity());

            orderDetails newOrder=orderDetailsRepo.save(storeOrder);
            return OrderDetailsDto.orderDetailsDto(newOrder);
        }
        return null;
    }
    public OrderDetailsDto UpdatePartialy(Integer oid, Integer pid, Map<String, Object> updates) {
        Optional<orderDetails> orderDetailsOptional = orderDetailsRepo.findById(new OrderDetailsId(oid, pid));
        if (orderDetailsOptional.isPresent()) {//true
            orderDetails order = orderDetailsOptional.get();
            if (updates.containsKey("totalPrice")) {
                Double total = null;
                try {
                    total = Double.parseDouble(updates.get("totalPrice").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                order.setTotalPrice(total);
            }
            if (updates.containsKey("price")) {
                Double p = null;
                try {
                    p = Double.parseDouble(updates.get("price").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                order.setPrice(p);
            }
            if (updates.containsKey("quantity")) {
                Integer q = null;
                try {
                    q = Integer.parseInt(updates.get("quantity").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                order.setQuantity(q);
            }
            orderDetails newOrder = orderDetailsRepo.save(order);
            return OrderDetailsDto.orderDetailsDto(newOrder);
        }
        return null;
    }




}
