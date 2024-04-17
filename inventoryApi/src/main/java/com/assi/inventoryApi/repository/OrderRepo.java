package com.assi.inventoryApi.repository;

import com.assi.inventoryApi.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.cust.cId = :customerId")
    List<Order> getCustOrdersById(@Param("customerId") Integer customerId);
}


