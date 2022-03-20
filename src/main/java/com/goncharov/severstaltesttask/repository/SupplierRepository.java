package com.goncharov.severstaltesttask.repository;

import com.goncharov.severstaltesttask.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
