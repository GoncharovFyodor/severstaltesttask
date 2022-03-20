package com.goncharov.severstaltesttask.dto;

import com.goncharov.severstaltesttask.model.product.Product;
import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String type;
    private String category;
    private String description;

    public ProductDto(Product model) {
        this.id = model.getId();
        this.type = model.getType().getName();
        this.category = model.getCategory().getName();
        this.description = model.getDescription();
    }

    public ProductDto(SupplyReport sqlResult) {
        this.id = sqlResult.getProductId();
        this.type = sqlResult.getProductType();
        this.category = sqlResult.getProductCategory();
        this.description = sqlResult.getProductDescription();
    }
}
