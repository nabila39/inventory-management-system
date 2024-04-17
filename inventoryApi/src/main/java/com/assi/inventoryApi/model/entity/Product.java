package com.assi.inventoryApi.model.entity;

import com.assi.inventoryApi.model.dto.ProDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Products")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;
    private String pName;
    private Float price;

    public static Product toEntity(ProDto dto){
        return Product.builder().
                pId(dto.getPId()).
                price(dto.getPrice()).
                pName(dto.getPName()).
                build();

    }
}
