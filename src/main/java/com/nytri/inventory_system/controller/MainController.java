package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.entity.Categories;
import com.nytri.inventory_system.entity.Stock;
import com.nytri.inventory_system.entity.Supplier;
import com.nytri.inventory_system.impl.ManageStockItems;
import com.nytri.inventory_system.repositories.CategoriesRepository;
import com.nytri.inventory_system.repositories.StockRepository;
import com.nytri.inventory_system.repositories.SupplierRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    private final StockRepository stockRepository;
    private final CategoriesRepository categoriesRepository;
    private final SupplierRepository supplierRepository;

    public MainController(StockRepository stockRepository, CategoriesRepository categoriesRepository,
                          SupplierRepository supplierRepository) {
        this.stockRepository = stockRepository;
        this.categoriesRepository = categoriesRepository;
        this.supplierRepository = supplierRepository;
    }

    // ! START STOCK METHODS
    @GetMapping("/api/stocks")
    public List<Stock> showAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/api/stocks/total_value")
    public double getInventoryValue(HttpServletResponse response) {
        ManageStockItems msi = new ManageStockItems(this.stockRepository.findAll());
        return msi.getTotalItemsPrice();
    }

    @GetMapping("/api/stocks/low_stock")
    public List<Stock> getLowStockItems() {
        return stockRepository.findAllLowStockItems();
    }

    @GetMapping("/api/stocks/no_stock")
    public List<Stock> getNoStockItems() {
        return stockRepository.findAllNoStockItems();
    }

    @PutMapping("/api/stocks/add")
    public void addStocks(@RequestBody Stock stock) {
        stockRepository.save(stock);
    }

    // ! END STOCK METHODS

    // ! START CATEGORIES METHOD.

    @GetMapping("/api/categories")
    public List<Categories> showAllCategories() {
        return categoriesRepository.findAll();
    }

    // ! END CATEGORIES METHOD.

    // ! START SUPPLIERS METHOD.

    @GetMapping("/api/suppliers")
    public List<Supplier> showAllSuppliers() { return supplierRepository.findAll(); }

    // ! END SUPPLIERS METHOD.
}
