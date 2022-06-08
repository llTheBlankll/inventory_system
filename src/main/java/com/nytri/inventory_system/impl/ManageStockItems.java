package com.nytri.inventory_system.impl;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ManageStockItems {

    private final List<Stock> allStocks;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ManageStockItems(List<Stock> allStocks) {
        this.allStocks = allStocks;
    }

    /*
    Get Products that has a quantity lower of 'Configuration.low_stock_items'.
    */
    public List<Stock> getLowStockItems() {
        List<Stock> lowStocks = new ArrayList<>();

        // * Check all if the product stock are low.
        allStocks.forEach(stock -> {
            int product_quantity = stock.getProductQuantity();

            if (product_quantity <= Configuration.low_stock_items) {
                lowStocks.add(stock);
            }
        });

        return lowStocks;
    }

    /*
    All the items and its quantity will be calculated to the total of the entire inventory.
    */
    public double getTotalItemsPrice() {
        double total = 0;

        for (Stock stock : this.allStocks) {
            total += (stock.getProductPrice() * stock.getProductQuantity());
        }

        logger.info("Total value of the inventory is " + total);

        return total;
    }
}
