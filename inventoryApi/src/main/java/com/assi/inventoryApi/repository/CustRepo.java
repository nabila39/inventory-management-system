package com.assi.inventoryApi.repository;

import com.assi.inventoryApi.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepo extends JpaRepository<Customer, Integer> {
}
