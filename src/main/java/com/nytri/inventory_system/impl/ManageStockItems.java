package com.nytri.inventory_system.impl;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ManageStockItems {

    private final Iterable<Stock> allStocks;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ManageStockItems(Iterable<Stock> allStocks) {
        this.allStocks = allStocks;
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
