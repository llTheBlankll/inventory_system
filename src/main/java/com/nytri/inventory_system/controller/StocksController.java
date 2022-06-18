package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.entity.Stock;
import com.nytri.inventory_system.impl.ManageStockItems;
import com.nytri.inventory_system.repositories.StockRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/stocks")
public class StocksController {

    private final StockRepository stockRepository;

    public StocksController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // ! START STOCK METHODS
    @GetMapping("/")
    public List<Stock> showAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/getStocksCount")
    public Integer getStocksCount() {
        return stockRepository.getStocksTotalCount();
    }

    @GetMapping("/total_value")
    public double getInventoryValue(HttpServletResponse response) {
        ManageStockItems msi = new ManageStockItems(this.stockRepository.findAll());
        return msi.getTotalItemsPrice();
    }

    @GetMapping("/low_stock")
    public List<Stock> getLowStockItems() {
        return stockRepository.findAllLowStockItems();
    }

    @GetMapping("/no_stock")
    public List<Stock> getNoStockItems() {
        return stockRepository.findAllNoStockItems();
    }

    @PutMapping("/add")
    public String addStocks(@RequestBody Stock stock) {
        // Check if the product already exist.
        if (this.stockRepository.findByProductName(stock.getProductName()) != null) {
            return "Product already exists";
        }

        stockRepository.save(stock);
        return "Product " + stock.getProductName() + " was saved successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStockById(@PathVariable("id") Integer id) {
        stockRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteStock(@RequestBody Stock stock) {
        stockRepository.delete(stock);
    }

    // ! END STOCK METHODS
}
