package com.nytri.inventory_system.controller;

import com.nytri.inventory_system.entity.Supplier;
import com.nytri.inventory_system.repositories.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/suppliers")
public class SuppliersController {

    private final SupplierRepository supplierRepository;

    public SuppliersController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // ! START SUPPLIERS METHOD.

    @GetMapping("/")
    public List<Supplier> showAllSuppliers() { return supplierRepository.findAll(); }

    // Get suppliers by their name containing the specified value.
    @GetMapping("/by_name")
    public Supplier getSupplierByName(@RequestParam("supplier_name") String supplierName) {
        return this.supplierRepository.findBySupplierName(supplierName);
    }

    @PutMapping("/add")
    public String addSupplier(@RequestBody Supplier supplier) {
        if (this.supplierRepository.findBySupplierName(supplier.getSupplierName()) != null) {
            return "Supplier " + supplier.getSupplierName() + " already exist.";
        }

        this.supplierRepository.save(supplier);
        return "Supplier " + supplier.getSupplierName() + " was added successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSupplierById(@PathVariable("id") int id) {
        supplierRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public String deleteSupplier(@RequestBody Supplier supplier) {
        if (this.supplierRepository.findBySupplierName(supplier.getSupplierName()) != null) {
            return "Supplier " + supplier.getSupplierName() + " doesn't exist.";
        }

        supplierRepository.delete(supplier);
        return "Supplier " + supplier.getSupplierName() + " successfully removed!";
    }

    // ! END SUPPLIERS METHOD.
}
