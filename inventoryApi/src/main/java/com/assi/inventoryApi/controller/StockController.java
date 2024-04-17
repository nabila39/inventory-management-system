package com.assi.inventoryApi.controller;

import com.assi.inventoryApi.model.dto.ProDto;
import com.assi.inventoryApi.model.dto.StockDto;
import com.assi.inventoryApi.service.ProService;
import com.assi.inventoryApi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stockLevels")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/getStockLevels")
    public List<StockDto> getStockLevels(){
        return stockService.getAllStockLevels();
    }
    @GetMapping("/getStockLevelById")
    public StockDto getStockLevelById(@RequestParam Integer id) {
        return stockService.getStockLevelById(id);
    }
    @PostMapping("/addStockLevel")
    public StockDto addStockLevel(
            @RequestParam Integer productId,
            @RequestBody StockDto stockDto) {
        return stockService.addStockLevel(productId, stockDto);
    }
    @DeleteMapping("/deleteStockLevel")
    public void deletById(@RequestParam Integer id) {
        stockService.deleteStockLevelByProductId(id);
    }
    @PutMapping("/updateStockFully")
    public StockDto updateProductFully(@RequestParam Integer id, @RequestBody StockDto stockDto) {
        return stockService.updateStockFully(id, stockDto);
    }
    @PatchMapping("/updateStockPartially")
    public StockDto updateStockPartially(@RequestParam Integer id, @RequestBody Map<String, Object> updates) {
        return stockService.updateProductPartially(id, updates);
    }
}
