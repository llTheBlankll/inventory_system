package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Stock;
import com.nytri.inventory_system.impl.ManageStockItems;
import com.nytri.inventory_system.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/stocks")
public class StocksController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StockRepository stockRepository;

    public StocksController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // ! START STOCK METHODS
    @GetMapping("/all")
    public Iterable<Stock> showAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/all/{page}")
    public Iterable<Stock> showAllStocksInPage(@PathVariable("page") Integer pageNum) {
        return stockRepository.findAll(PageRequest.of(pageNum, Configuration.stock_pagination_max_data)).getContent();
    }

    @GetMapping("/getStocksCount")
    public Integer getStocksCount() {
        return stockRepository.getStocksTotalCount();
    }

    @GetMapping("/total_value")
    public double getInventoryValue() {
        ManageStockItems msi = new ManageStockItems(this.stockRepository.findAll());
        return msi.getTotalItemsPrice();
    }

    @GetMapping("/low_stock")
    public Iterable<Stock> getLowStockItems() {
        return stockRepository.findAllLowStockItems();
    }

    @GetMapping("/no_stock")
    public Iterable<Stock> getNoStockItems() {
        return stockRepository.findAllNoStockItems();
    }

    @PutMapping("/add")
    public String addStocks(@RequestBody Stock stock) {
        // Check if the product already exist.
        if (this.stockRepository.findByProductName(stock.getProductName()) != null) {
            logger.info("Product " + stock.getProductName() + " already exist.");
            return "Product already exists";
        }

        stockRepository.save(stock);
        logger.info("Product " + stock.getProductName() + " was successfully saved!");
        return "Product " + stock.getProductName() + " was saved successfully!";
    }

    @PutMapping("/update")
    public String updateStock(@RequestBody Stock stock) {
        // If the product doesn't exist, return an error.
        if (this.stockRepository.findByProductName(stock.getProductName()) == null) {
            logger.info("Product " + stock.getProductName() + " doesn't exist. ");
            return "Product " + stock.getProductName() + " doesn't exist.";
        }

        // Check if the product already exist.
        if (this.stockRepository.findByProductName(stock.getProductName()) != null) {
            logger.info("Product " + stock.getProductName() + " already exist.");
            return "Product already exists";
        }

        stockRepository.save(stock);
        logger.info("Product " + stock.getProductName() + " was updated successfully!");
        return "Product " + stock.getProductName() + " was updated successfully!";
    }


    @DeleteMapping("/delete/{id}")
    public void deleteStockById(@PathVariable("id") Integer id) {
        logger.warn("Item with an ID of " + id + " was deleted.");
        stockRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteStock(@RequestBody Stock stock) {
        logger.warn("Item with a name of " + stock.getProductName() + " was deleted.");
        stockRepository.delete(stock);
    }

    // ! END STOCK METHODS
}
