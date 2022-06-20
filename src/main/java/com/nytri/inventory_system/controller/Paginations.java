package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.repositories.CategoriesRepository;
import com.nytri.inventory_system.repositories.StockRepository;
import com.nytri.inventory_system.repositories.SupplierRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/pagination/numbering")
public class Paginations {

  private final StockRepository stockRepository;
  private final CategoriesRepository categoriesRepository;
  private final SupplierRepository supplierRepository;

  public Paginations(StockRepository stockRepository,
                     SupplierRepository supplierRepository,
                     CategoriesRepository categoriesRepository) {
    this.categoriesRepository = categoriesRepository;
    this.supplierRepository = supplierRepository;
    this.stockRepository = stockRepository;
  }

  @GetMapping("/stocks/{page}")
  public List<Integer> getStocksPaginationNumbersIfInPage(@PathVariable("page") Integer pageNum) {
    List<Integer> pagination = new ArrayList<>();
    if (pageNum - 1 >= 0) {
      if (pageNum - 2 >= 0) {
        pagination.add(pageNum - 2);
      }
      pagination.add(pageNum - 1);
    }

    pagination.add(pageNum);
    if (stockRepository.findAll(PageRequest.of(pageNum + 1, Configuration.stock_pagination_max_data)).hasContent()) {
      if (stockRepository.findAll(PageRequest.of(pageNum + 2, Configuration.stock_pagination_max_data)).hasContent()) {
        pagination.add(pageNum + 2);
      }

      pagination.add(pageNum + 1);
    }

    return pagination;
  }

  @GetMapping("/categories/{page}")
  public List<Integer> getCategoriesPaginationNumbersIfInPage(@PathVariable("page") Integer pageNum) {
    List<Integer> pagination = new ArrayList<>();

    if (pageNum - 1 >= 0) {
      if (pageNum - 2 >= 0) {
        pagination.add(pageNum - 2);
      }
      pagination.add(pageNum - 1);
    }

    pagination.add(pageNum);

    if (categoriesRepository.findAll(PageRequest.of(pageNum + 1, Configuration.categories_pagination_max_data)).hasContent()) {
      if (categoriesRepository.findAll(PageRequest.of(pageNum + 2, Configuration.stock_pagination_max_data)).hasContent()) {
        pagination.add(pageNum + 2);
      }

      pagination.add(pageNum + 1);
    }

    return pagination;
  }

  @GetMapping("/suppliers/{page}")
  public List<Integer> getSuppliersPaginationNumbersIfInPage(@PathVariable("page") Integer pageNum) {
    List<Integer> pagination = new ArrayList<>();

    if (pageNum - 1 >= 0) {
      if (pageNum - 2 >= 0) {
        pagination.add(pageNum + 2);
      }

      pagination.add(pageNum + 1);
    }

    pagination.add(pageNum);

    if (supplierRepository.findAll(PageRequest.of(pageNum + 1, Configuration.suppliers_pagination_max_data)).hasContent()) {
      if (supplierRepository.findAll(PageRequest.of(pageNum + 2, Configuration.suppliers_pagination_max_data)).hasContent()) {
        pagination.add(pageNum + 2);
      }

      pagination.add(pageNum + 1);
    }

    return pagination;
  }
}
