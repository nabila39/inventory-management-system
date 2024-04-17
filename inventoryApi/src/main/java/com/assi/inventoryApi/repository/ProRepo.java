package com.assi.inventoryApi.repository;

import com.assi.inventoryApi.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProRepo extends JpaRepository<Product, Integer> {
}
