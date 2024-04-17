package com.assi.inventoryApi.service;

import com.assi.inventoryApi.model.dto.CustDto;
import com.assi.inventoryApi.model.dto.ProDto;
import com.assi.inventoryApi.model.entity.Customer;
import com.assi.inventoryApi.model.entity.Product;
import com.assi.inventoryApi.repository.CustRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustService {
    @Autowired
    private CustRepo cstRepo;

    public List<CustDto> getAllCustomers() {
        List<Customer> customers = cstRepo.findAll();
        return customers.stream()
                .map(CustDto::custToDto)
                .collect(Collectors.toList());
    }
    public CustDto getCustomerById(Integer id) {
        Optional<Customer> customerOptional = cstRepo.findById(id);
        return customerOptional.map(CustDto::custToDto).orElse(null);
    }

    public CustDto addNewCustomer(@RequestBody CustDto cust) {
        return CustDto.custToDto(cstRepo.save(Customer.toEntity(cust)));
    }
    public void deleteCustomerById(Integer id) {
        this.cstRepo.deleteById(id);

    }
    public CustDto updateCustomerPartially(Integer id, Map<String, Object> updates) {
        Optional<Customer> customerOptional = cstRepo.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (updates.containsKey("cname")) {
                customer.setCName((String) updates.get("cname"));
            }
            if (updates.containsKey("phoneNumber")) {
                customer.setPhoneNumber((String) updates.get("phoneNumber"));
            }
            if (updates.containsKey("address")) {
                customer.setAddress((String) updates.get("address"));
            }

            Customer updatedCustomer = cstRepo.save(customer);
            return CustDto.custToDto(updatedCustomer);
        }
        return null;
    }
    public CustDto updateCustomerFully(Integer id, CustDto custDto) {
        Optional<Customer> optionalCustomer = cstRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCName(custDto.getCName());
            customer.setPhoneNumber(custDto.getPhoneNumber());
            customer.setAddress(custDto.getAddress());
            Customer updatedCustomer = cstRepo.save(customer);
            return CustDto.custToDto(updatedCustomer);
        }
        return null;
    }}
