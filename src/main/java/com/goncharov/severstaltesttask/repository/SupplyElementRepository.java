package com.goncharov.severstaltesttask.repository;

import com.goncharov.severstaltesttask.model.supply.SupplyElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyElementRepository extends JpaRepository<SupplyElement, Long> {
}
