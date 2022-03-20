package com.goncharov.severstaltesttask.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность "поставщик"
 */
@Entity
@Table(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
public class Supplier {

    @Id
    private Integer id;

    /**
     * Имя
     */
    private String name;

    /**
     * ИНН
     */
    private Long inn;

    /**
     * Адрес
     */
    private String address;
}
