package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.entity.Categories;
import com.nytri.inventory_system.entity.Stock;
import com.nytri.inventory_system.repositories.CategoriesRepository;
import com.nytri.inventory_system.repositories.StockRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    private final StockRepository stockRepository;
    private final CategoriesRepository categoriesRepository;

    public MainController(StockRepository stockRepository, CategoriesRepository categoriesRepository) {
        this.stockRepository = stockRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/api/stocks")
    public List<Stock> showAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/api/categories")
    public List<Categories> showAllCategories() {
        return categoriesRepository.findAll();
    }
}
