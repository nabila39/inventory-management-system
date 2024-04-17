package com.assi.inventoryApi.model.dto;

import com.assi.inventoryApi.model.entity.Product;
import com.assi.inventoryApi.model.entity.Stock;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Integer pId;
    private Integer quantity;
    private String pName;

    public static StockDto stockToDto(Stock stock){
        return StockDto.builder().
                pId(stock.getPId()).
                pName(stock.getPName()).
                quantity(stock.getQuantity()).
                build();

    }


}
