package com.assi.inventoryApi.model.entity;

import com.assi.inventoryApi.model.dto.CustDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Customers")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;
    private String cName;
    private String phoneNumber;
    private String address;

    public static Customer toEntity(CustDto dto){
        return Customer.builder().
                cId(dto.getCId()).
                cName(dto.getCName()).
                phoneNumber(dto.getPhoneNumber()).
                address(dto.getAddress()).
                build();
    }
}
