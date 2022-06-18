package com.nytri.inventory_system.repositories;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Integer> {

    @Query(value = "SELECT * FROM stocks WHERE product_quantity = 0", nativeQuery = true)
    List<Stock> findAllNoStockItems();

    @Query(value = "SELECT * FROM stocks WHERE product_quantity <= " + Configuration.low_stock_items_max_row, nativeQuery = true)
    List<Stock> findAllLowStockItems();

    Stock findByProductName(String product_name);

    List<Stock> findByProductNameContaining(String product_name);

    @Query(value = "SELECT COUNT(*) FROM stocks", nativeQuery = true)
    Integer getStocksTotalCount();
}