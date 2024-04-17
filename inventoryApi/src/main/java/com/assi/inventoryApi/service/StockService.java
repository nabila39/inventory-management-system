package com.assi.inventoryApi.service;

import com.assi.inventoryApi.model.dto.StockDto;
import com.assi.inventoryApi.model.entity.Product;
import com.assi.inventoryApi.model.entity.Stock;
import com.assi.inventoryApi.repository.ProRepo;
import com.assi.inventoryApi.repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private ProRepo proRepo;
    public List<StockDto> getAllStockLevels() {
        List<Stock> stock = stockRepo.findAll();
        return stock.stream()
                .map(StockDto::stockToDto)
                .collect(Collectors.toList());
    }
    public StockDto getStockLevelById(Integer id) {
        Optional<Stock> stockOptional = stockRepo.findById(id);
        return stockOptional.map(StockDto::stockToDto).orElse(null);
    }
    public StockDto addStockLevel(Integer productId, StockDto stockDto) {
        Optional<Product> productOptional = proRepo.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();


            Stock newStock = new Stock();
            newStock.setPId(productId);
            newStock.setQuantity(stockDto.getQuantity());
            newStock.setPName(product.getPName());


            Stock savedStock = stockRepo.save(newStock);


            return StockDto.stockToDto(savedStock);
        } else {
            return null;
        }
    }
    public void deleteStockLevelByProductId(Integer productId) {
        stockRepo.deleteById(productId);
    }
    public StockDto updateStockFully(Integer id, StockDto stockDto) {
        Optional<Stock> optionalStock= stockRepo.findById(id);
        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            stock.setPName(stockDto.getPName());
            stock.setQuantity(stockDto.getQuantity());
            Stock updatedStock = stockRepo.save(stock);
            return StockDto.stockToDto(updatedStock);
        }
        return null;
    }
    public StockDto updateProductPartially(Integer id, Map<String, Object> updates) {
        Optional<Stock> optionalStock = stockRepo.findById(id);
        if (optionalStock.isPresent()) {//true
            Stock stock = optionalStock.get();
            if (updates.containsKey("pname")) {
                    stock.setPName((String) updates.get("pname"));
            }
            if (updates.containsKey("quantity")) {
                Integer quantity = null;
                try {
                    quantity = Integer.parseInt(updates.get("quantity").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                stock.setQuantity(quantity);
            }
            Stock updatedStock = stockRepo.save(stock);
            return StockDto.stockToDto(updatedStock);
        }
        return null;
    }



}
