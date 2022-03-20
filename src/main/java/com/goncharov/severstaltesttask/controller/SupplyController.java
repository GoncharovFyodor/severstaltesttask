package com.goncharov.severstaltesttask.controller;

import com.goncharov.severstaltesttask.dto.ReportDto;
import com.goncharov.severstaltesttask.dto.SupplyCreateDto;
import com.goncharov.severstaltesttask.dto.SupplyDto;
import com.goncharov.severstaltesttask.service.SupplyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/supply")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplyDto> createSupply(@RequestBody SupplyCreateDto req) {
        return ResponseEntity.ok(
                new SupplyDto(service.createSupply(req))
        );
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getReportByPeriod(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to) {
        return ResponseEntity.ok(
                service.getReportByPeriod(from, to).stream()
                        .map(ReportDto::new)
                        .collect(Collectors.toList())
        );
    }

}
