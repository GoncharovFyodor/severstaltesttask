package com.goncharov.severstaltesttask.model.supply;

public interface SupplyReport {
    Long getProductId();

    String getProductDescription();

    String getProductType();

    String getProductCategory();

    Integer getSupplierId();

    String getSupplierName();

    Long getSupplierInn();

    String getSupplierAddress();

    Long getTotalQuantity();

    Long getTotalSum();
}
