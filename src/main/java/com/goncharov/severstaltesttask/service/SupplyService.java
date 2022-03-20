package com.goncharov.severstaltesttask.service;

import com.goncharov.severstaltesttask.dto.SupplyCreateDto;
import com.goncharov.severstaltesttask.model.Supplier;
import com.goncharov.severstaltesttask.model.supply.Supply;
import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import com.goncharov.severstaltesttask.model.supply.SupplyWithSum;

import java.time.LocalDate;
import java.util.List;

public interface SupplyService {
    Supply createSupply(SupplyCreateDto req);
    List<SupplyReport> getReportByPeriod(LocalDate from, LocalDate to);
    List<Supplier> getAllSuppliers();
    List<SupplyWithSum> getLastSupplyWithSum(Integer amount);
}
