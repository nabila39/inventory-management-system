package com.assi.inventoryApi.model.entity;

import com.assi.inventoryApi.model.dto.ProDto;
import com.assi.inventoryApi.model.dto.StockDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "stock")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    private Integer pId;

    @OneToOne
    @JoinColumn(name = "pId")
    private Product product;


    private Integer quantity;


    private String pName;

    public static Stock toEntity(StockDto dto){
        return Stock.builder().
                pId(dto.getPId()).
                pName(dto.getPName()).
                quantity(dto.getQuantity()).
                build();

    }


}


