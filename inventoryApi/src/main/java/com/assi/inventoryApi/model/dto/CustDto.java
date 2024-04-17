package com.assi.inventoryApi.model.dto;
import com.assi.inventoryApi.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustDto {
    private Integer cId;
    private String cName;
    private String phoneNumber;
    private String address;
    public static CustDto custToDto(Customer cust){
        return CustDto.builder().
                cId(cust.getCId()).
                cName(cust.getCName()).
                phoneNumber(cust.getPhoneNumber()).
                address(cust.getAddress()).
                build();

    }

}
