package com.goncharov.severstaltesttask.dto;

import com.goncharov.severstaltesttask.model.Supplier;
import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDto {
    private Integer id;
    private String name;
    private Long inn;
    private String address;

    public SupplierDto(Supplier model){
        this.id = model.getId();
        this.name = model.getName();
        this.inn = model.getInn();
        this.address = model.getAddress();
    }

    public SupplierDto(SupplyReport sqlResult) {
        this.id = sqlResult.getSupplierId();
        this.name = sqlResult.getSupplierName();
        this.inn = sqlResult.getSupplierInn();
        this.address = sqlResult.getSupplierAddress();
    }
}
