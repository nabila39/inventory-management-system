package com.assi.inventoryApi.repository;

import com.assi.inventoryApi.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StockRepo extends JpaRepository <Stock, Integer>{
}
