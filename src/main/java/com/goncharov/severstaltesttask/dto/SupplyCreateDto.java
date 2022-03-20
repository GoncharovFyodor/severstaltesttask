package com.goncharov.severstaltesttask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupplyCreateDto {
    private Integer supplierId;
    private List<SupplyElementCreateDto> elements;
}
