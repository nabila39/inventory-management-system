package com.assi.inventoryApi.controller;

import com.assi.inventoryApi.model.dto.OrderDetailsDto;
import com.assi.inventoryApi.model.dto.OrderDto;
import com.assi.inventoryApi.model.dto.StockDto;
import com.assi.inventoryApi.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @GetMapping("/getOrderDetails")
    public List<OrderDetailsDto> getOrderDetails(){
        return orderDetailsService.getAllOrderDetails();
    }
    @GetMapping("/getOrderDetailsById")
    public OrderDetailsDto getOrderDetailsById(@RequestParam Integer oid,@RequestParam Integer pid) {
        return orderDetailsService.getById(oid,pid);
    }
    @PostMapping("/addOrderDetails")
    public OrderDetailsDto addOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) {
        return orderDetailsService.addNewOrderDetails(orderDetailsDto);
    }
    @DeleteMapping("/deleteOrderDetails")
    public void deleteOrderDetailsById(@RequestParam Integer oid,@RequestParam Integer pid) {
        orderDetailsService.deleteOrderDetailsById(oid,pid);
    }
    @PutMapping("/updateOrderDetailsFully")
    public OrderDetailsDto updateOrderDetailsFully(@RequestBody OrderDetailsDto orderDetailsDto,@RequestParam Integer oid,@RequestParam Integer pid) {
        return orderDetailsService.updateFully(orderDetailsDto,oid,pid);
    }
    @PatchMapping("/updateOrderDetailsPartially")
    public OrderDetailsDto updateOrderDetailsPartially(@RequestParam Integer oid,@RequestParam Integer pid, @RequestBody Map<String, Object> updates) {
        return orderDetailsService.UpdatePartialy(oid,pid, updates);
    }


}
