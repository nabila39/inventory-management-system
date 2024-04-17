package com.assi.inventoryApi.controller;

import com.assi.inventoryApi.model.dto.CustDto;
import com.assi.inventoryApi.model.dto.OrderDto;
import com.assi.inventoryApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/getOrders")
    public List<OrderDto> getAllOrderss(){
        return orderService.getAllOrderss();
    }
    @GetMapping("/getOrderById")
    public OrderDto getOrderById(@RequestParam Integer id) {
        return orderService.getOrderById(id);
    }
    @GetMapping("/getCustOrdersById")
    public List<OrderDto> getCustOrdersById(@RequestParam Integer id) {
        return orderService.getCustOrdersById(id);
    }
    @PostMapping("/addOrder")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        orderDto.setOrderDate(new Date());
        return orderService.addNewOrder(orderDto);
    }
    @DeleteMapping("/deleteOrder")
    public void deleteOrderById(@RequestParam Integer id) {
        orderService.deleteOrderById(id);
    }
    @PatchMapping("/updateOrderPartially")
    public OrderDto updateOrderPartially(@RequestParam Integer id, @RequestBody Map<String, Object> updates) {
        return orderService.UpdatePartialy(id, updates);
    }
    @PutMapping("/updateOrderFully")
    public OrderDto updateOrderFully(@RequestBody OrderDto orderDto,@RequestParam Integer id) {
        return orderService.updateFully(orderDto,id);
    }

}
