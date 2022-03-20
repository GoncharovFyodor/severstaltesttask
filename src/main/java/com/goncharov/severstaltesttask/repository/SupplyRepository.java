package com.goncharov.severstaltesttask.repository;

import com.goncharov.severstaltesttask.model.supply.Supply;
import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import com.goncharov.severstaltesttask.model.supply.SupplyWithSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    @Query(nativeQuery = true,
            value = "SELECT p.id                      AS productId,"
                    + "       p.description               AS productDescription,"
                    + "       pt.name                     AS productType,"
                    + "       pc.name                     AS productCategory,"
                    + "       spr.id                      AS supplierId,"
                    + "       spr.name                    AS supplierName,"
                    + "       spr.inn                     AS supplierInn,"
                    + "       spr.address                 AS supplierAddress,"
                    + "       sum(se.quantity)            AS totalQuantity,"
                    + "       sum(se.price * se.quantity) AS totalSum"
                    + " FROM supply s"
                    + "         LEFT JOIN supplier spr ON s.supplier_id = spr.id"
                    + "         LEFT JOIN supply_element se ON s.id = se.supply_id"
                    + "         LEFT JOIN product p ON p.id = se.product_id"
                    + "         LEFT JOIN product_category pc ON p.category_id = pc.id"
                    + "         LEFT JOIN product_type pt ON p.type_id = pt.id"
                    + " WHERE s.created BETWEEN :from AND :to"
                    + " GROUP BY p.id, pc.id, pt.id, spr.id"
                    + " ORDER BY p.id ASC, spr.id asc")
    List<SupplyReport> getReportByPeriod(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(nativeQuery = true,
            value = "SELECT s.id                      AS id,"
                    + "       s.created                   AS created,"
                    + "       spr.name                    AS supplier,"
                    + "       sum(se.price * se.quantity) AS sum"
                    + " FROM supply s"
                    + "         LEFT JOIN supplier spr ON s.supplier_id = spr.id"
                    + "         LEFT JOIN supply_element se ON s.id = se.supply_id"
                    + " GROUP BY s.id, spr.name, s.created"
                    + " ORDER BY s.created desc"
                    + " LIMIT :amount")
    List<SupplyWithSum> getLastSupplyWithSum(@Param("amount") Integer amount);
}
