package com.goncharov.severstaltesttask.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
public class ProductCategory {
    @Id
    private Integer id;

    private String name;
}
