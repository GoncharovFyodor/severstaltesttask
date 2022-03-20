package com.goncharov.severstaltesttask.model.supply;

import com.goncharov.severstaltesttask.model.WeightMeasure;
import com.goncharov.severstaltesttask.model.product.Product;
import com.goncharov.severstaltesttask.model.product.ProductPrice;
import com.goncharov.severstaltesttask.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supply_element")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@NoArgsConstructor
public class SupplyElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supply_id", referencedColumnName = "id")
    private Supply supply;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer quantity;

    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "weight_measure")
    @Type(type = "pgsql_enum")
    private WeightMeasure weightMeasure;

    public SupplyElement(ProductPrice productPrice, Integer quantity, Supply supply) {
        this.product = productPrice.getProduct();
        this.price = productPrice.getPrice();
        this.weightMeasure = productPrice.getWeightMeasure();
        this.quantity = quantity;
        this.supply = supply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyElement that = (SupplyElement) o;
        return Objects.equals(supply, that.supply) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price)
                && weightMeasure == that.weightMeasure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supply, product, quantity, price, weightMeasure);
    }
}
