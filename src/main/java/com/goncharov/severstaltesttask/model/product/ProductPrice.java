package com.goncharov.severstaltesttask.model.product;

import com.goncharov.severstaltesttask.model.Supplier;
import com.goncharov.severstaltesttask.model.WeightMeasure;
import com.goncharov.severstaltesttask.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product_price")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@NoArgsConstructor
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "period_start")
    private LocalDate periodStart;

    @Column(name = "period_end")
    private LocalDate periodEnd;

    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "weight_measure")
    @Type(type = "pgsql_enum")
    private WeightMeasure weightMeasure;
}
