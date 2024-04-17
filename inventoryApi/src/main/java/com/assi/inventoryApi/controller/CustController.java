package com.assi.inventoryApi.controller;
import com.assi.inventoryApi.model.dto.CustDto;
import com.assi.inventoryApi.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustController {
    @Autowired
    private CustService custService;

    @GetMapping("/getCustomers")
    public List<CustDto> getAllCustomers(){
        return custService.getAllCustomers();
    }

    @GetMapping("/getCustomerById")
    public CustDto getCustomerById(@RequestParam Integer id) {
        return custService.getCustomerById(id);
    }
    @PostMapping("/addCustomer")
    public CustDto addCustomer(@RequestBody CustDto custDto) {
        return custService.addNewCustomer(custDto);
    }
    @DeleteMapping("/deleteCustomer")
    public void deletCustomerById(@RequestParam Integer id) {
        custService.deleteCustomerById(id);
    }

    @PatchMapping("/updateCustomerPartially")
    public CustDto updateCustomerPartially(@RequestParam Integer id, @RequestBody Map<String, Object> updates) {
        return custService.updateCustomerPartially(id, updates);
    }
    @PutMapping("/updateCustomerFully")
    public CustDto updateCustomerFully(@RequestParam Integer id, @RequestBody CustDto custDto) {
        return custService.updateCustomerFully(id, custDto);
    }
}
