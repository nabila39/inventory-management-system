package com.assi.inventoryApi.model.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailsId implements Serializable {
    private Integer orderId;
    private Integer pId;
}
