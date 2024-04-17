package com.assi.inventoryApi.repository;


import com.assi.inventoryApi.model.entity.OrderDetailsId;
import com.assi.inventoryApi.model.entity.orderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface orderDetailsRepo extends JpaRepository<orderDetails, OrderDetailsId> {

}
