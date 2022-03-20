package com.goncharov.severstaltesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyElementCreateDto {
    private Long productId;
    private Integer quantity;
}
