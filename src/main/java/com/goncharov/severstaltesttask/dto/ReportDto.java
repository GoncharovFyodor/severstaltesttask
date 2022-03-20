package com.goncharov.severstaltesttask.dto;

import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDto {
    private ProductDto product;
    private SupplierDto supplier;
    private Long totalQuantity;
    private Long totalSum;

    public ReportDto(SupplyReport sqlResult){
        this.product = new ProductDto(sqlResult);
        this.supplier = new SupplierDto(sqlResult);
        this.totalQuantity = sqlResult.getTotalQuantity();
        this.totalSum = sqlResult.getTotalSum();
    }

}
