package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Supplier;
import com.nytri.inventory_system.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/suppliers")
public class SuppliersController {

    private final SupplierRepository supplierRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SuppliersController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // ! START SUPPLIERS METHOD.

    @GetMapping("/all")
    public List<Supplier> showAllSuppliers() { return supplierRepository.findAll(); }

    @GetMapping("/all/{page}")
    public List<Supplier> showAllSuppliersAtPage(@PathVariable("page") int pageNum) {
        return supplierRepository.findAll(PageRequest.of(pageNum, Configuration.suppliers_pagination_max_data)).getContent();
    }

    // Get suppliers by their name containing the specified value.
    @GetMapping("/by_name")
    public Supplier getSupplierByName(@RequestParam("supplier_name") String supplierName) {
        return this.supplierRepository.findBySupplierName(supplierName);
    }

    @PutMapping("/add")
    public String addSupplier(@RequestBody Supplier supplier) {
        // Check if the supplier exist, if it is, then return an error.
        if (this.supplierRepository.findBySupplierName(supplier.getSupplierName()) != null) {
            return "Supplier " + supplier.getSupplierName() + " already exist.";
        }

        this.supplierRepository.save(supplier);
        return "Supplier " + supplier.getSupplierName() + " was added successfully!";
    }

    @PutMapping("/update")
    public String updateSupplier(@RequestBody Supplier supplier) {
        // Check if the supplier exist, if not, the return an error.
        if (this.supplierRepository.findBySupplierName(supplier.getSupplierName()) != null) {
            logger.info("Supplier " + supplier.getSupplierName() + " doesn't exist.");
            return "Supplier " + supplier.getSupplierName() + " doesn't exist.";
        }

        this.supplierRepository.save(supplier);
        logger.info("Supplier " + supplier.getSupplierName() + " was updated successfully!");
        return "Supplier " + supplier.getSupplierName() + " was updated successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSupplierById(@PathVariable("id") int id) {
        supplierRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public String deleteSupplier(@RequestBody Supplier supplier) {
        if (this.supplierRepository.findBySupplierName(supplier.getSupplierName()) == null) {
            logger.info("Supplier " + supplier.getSupplierName() + " doesn't exist so it cannot be deleted.");
            return "Supplier " + supplier.getSupplierName() + " doesn't exist.";
        }

        supplierRepository.delete(supplier);
        logger.warn("Supplier " + supplier.getSupplierName() + " successfully removed!");
        return "Supplier " + supplier.getSupplierName() + " successfully removed!";
    }

    // ! END SUPPLIERS METHOD.
}
