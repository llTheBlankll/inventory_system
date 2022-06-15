package com.nytri.inventory_system.repositories;

import com.nytri.inventory_system.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    List<Categories> findByCategoryNameContaining(String category_name);
    List<Categories> findByCategoryDescriptionContaining(String description);

    Categories findByCategoryName(String category_name);
}