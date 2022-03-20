package com.goncharov.severstaltesttask.dto;

import com.goncharov.severstaltesttask.model.supply.SupplyElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplyElementDto {
    private Long id;
    private ProductDto productDto;
    private Integer quantity;
    private Double price;
    private String weightMeasure;

    public SupplyElementDto(SupplyElement model) {
        this.id = model.getId();
        this.productDto = new ProductDto(model.getProduct());
        this.quantity = model.getQuantity();
        this.price = model.getPrice();
        this.weightMeasure = model.getWeightMeasure().toString();
    }
}
