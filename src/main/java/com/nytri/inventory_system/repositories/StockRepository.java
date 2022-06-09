package com.nytri.inventory_system.repositories;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    @Query(value = "SELECT * FROM stocks WHERE product_quantity = 0", nativeQuery = true)
    List<Stock> findAllNoStockItems();

    @Query(value = "SELECT * FROM stocks WHERE product_quantity <= " + Configuration.low_stock_items_max_row, nativeQuery = true)
    List<Stock> findAllLowStockItems();
}