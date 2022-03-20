package com.goncharov.severstaltesttask.service;

import com.goncharov.severstaltesttask.dto.SupplyCreateDto;
import com.goncharov.severstaltesttask.dto.SupplyElementCreateDto;
import com.goncharov.severstaltesttask.exception.EntityNotFoundException;
import com.goncharov.severstaltesttask.exception.ValidationException;
import com.goncharov.severstaltesttask.model.Supplier;
import com.goncharov.severstaltesttask.model.supply.Supply;
import com.goncharov.severstaltesttask.model.supply.SupplyElement;
import com.goncharov.severstaltesttask.model.supply.SupplyReport;
import com.goncharov.severstaltesttask.model.supply.SupplyWithSum;
import com.goncharov.severstaltesttask.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class SupplyServiceImpl implements SupplyService{
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final SupplyRepository supplyRepository;
    private final SupplyElementRepository supplyElementRepository;

    @Override
    @Transactional
    public Supply createSupply(SupplyCreateDto req) {
        if (isNull(req.getElements()) || req.getElements().isEmpty()){
            throw new ValidationException("Elements must not be empty");
        }
        if (isNull(req.getSupplierId())){
            throw new ValidationException("SupplierId must not be empty");
        }
        var supplier = supplierRepository.findById(req.getSupplierId())
                .orElseThrow(()->new EntityNotFoundException("Supplier not found"));

        var prices = productRepository.getProductPrices(
                req.getSupplierId(),
                req.getElements().stream()
                        .map(SupplyElementCreateDto::getProductId)
                        .collect(Collectors.toList()),
                LocalDate.now()
        );

        var result = new Supply(supplier);
        supplyRepository.saveAndFlush(result);

        var supplyElements = unionEqualsProducts(req.getElements()).entrySet().stream()
                .map(it -> {
                    var productPrice = prices.stream()
                            .filter(price -> Objects.equals(it.getKey(), price.getProduct().getId()))
                            .findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("Price for productId " + it.getKey() + " not found"));

                    return new SupplyElement(productPrice, it.getValue(), result);
                })
                .collect(Collectors.toSet());
        supplyElementRepository.saveAllAndFlush(supplyElements);

        result.setElements(supplyElements);
        return result;
    }

    private Map<Long, Integer> unionEqualsProducts(List<SupplyElementCreateDto> elements) {
        Map<Long, Integer> result = new HashMap<>();

        elements.forEach(it -> {

            if (it.getQuantity() <= 0) {
                throw new ValidationException("Quantity < 0");
            }

            if (result.containsKey(it.getProductId())) {
                var oldQuantity = result.get(it.getProductId());
                result.put(it.getProductId(), oldQuantity + it.getQuantity());
            } else {
                result.put(it.getProductId(), it.getQuantity());
            }
        });

        return result;
    }

    @Override
    public List<SupplyReport> getReportByPeriod(LocalDate from, LocalDate to) {
        return supplyRepository.getReportByPeriod(from.atStartOfDay(),to.atStartOfDay().plusHours(24));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public List<SupplyWithSum> getLastSupplyWithSum(Integer amount) {
        return supplyRepository.getLastSupplyWithSum(amount);
    }
}
