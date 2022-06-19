package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.repositories.StockRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/pagination/numbering")
public class Paginations {

    private final StockRepository stockRepository;

    public Paginations(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/stocks/{page}")
    public List<Integer> getPaginationNumbersIfInPage(@PathVariable("page") Integer pageNum) {
        Integer total_rows = stockRepository.getStocksTotalCount();
        List<Integer> pagination = new ArrayList<>();
        if (pageNum - 1 >= 0) {
            if (pageNum - 2 >= 0) {
                pagination.add(pageNum - 2);
            }
            pagination.add(pageNum - 1);
        }

        pagination.add(pageNum);
        if (stockRepository.findAll(PageRequest.of(pageNum + 1, Configuration.stock_pagination_max_row)).hasContent()) {
            pagination.add(pageNum + 1);
        }

        if (stockRepository.findAll(PageRequest.of(pageNum + 2, Configuration.stock_pagination_max_row)).hasContent()) {
            pagination.add(pageNum + 2);
        }

        return pagination;
    }
}
