package com.goncharov.severstaltesttask.repository;

import com.goncharov.severstaltesttask.model.product.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductPrice, Long> {
    @Query(nativeQuery = true,
            value = "SELECT pp.* "
                    + "FROM product_price pp "
                    + "WHERE pp.supplier_id = :supplierId "
                    + "AND pp.product_id IN (:productIds) "
                    + "AND :currentData BETWEEN pp.period_start AND pp.period_end "
    )
    List<ProductPrice> getProductPrices(
            @Param("supplierId") Integer supplierId,
            @Param("productIds") List<Long> productIds,
            @Param("currentData") LocalDate currentData);
}
