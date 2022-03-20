package com.goncharov.severstaltesttask.dto;

import com.goncharov.severstaltesttask.model.supply.Supply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SupplyDto {
    private Long id;
    private SupplierDto supplier;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<SupplyElementDto> elements;

    public SupplyDto(Supply model) {
        this.id = model.getId();
        this.supplier = new SupplierDto(model.getSupplier());
        this.created = model.getCreated();
        this.updated = model.getUpdated();
        this.elements = model.getElements().stream()
                .map(SupplyElementDto::new)
                .collect(Collectors.toList());
    }
}
