package com.assi.inventoryApi.model.dto;

import com.assi.inventoryApi.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProDto {
    private Integer pId;
    private String pName;
    private Float price;

    public static ProDto toDto(Product pro){
        return ProDto.builder().
                pId(pro.getPId()).
                price(pro.getPrice()).
                pName(pro.getPName()).
                build();

    }
}
