package com.goncharov.severstaltesttask.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
public class ProductType {
    @Id
    private Integer id;

    private String name;
}
