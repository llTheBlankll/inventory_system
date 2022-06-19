package com.nytri.inventory_system.repositories;

import com.nytri.inventory_system.entity.Categories;
import com.nytri.inventory_system.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findBySupplierName(String supplier_name);
}