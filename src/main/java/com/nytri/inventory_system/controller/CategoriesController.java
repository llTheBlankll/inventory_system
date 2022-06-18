package com.nytri.inventory_system.controller;


import com.nytri.inventory_system.entity.Categories;
import com.nytri.inventory_system.repositories.CategoriesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesRepository categoriesRepository;

    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    // ! START CATEGORIES METHOD.

    @GetMapping("/api/categories")
    public List<Categories> showAllCategories() {
        return categoriesRepository.findAll();
    }

    @GetMapping("/api/categories/by_name")
    public Categories getCategoryByName(@RequestParam("name") String name) {
        return this.categoriesRepository.findByCategoryName(name);
    }

    // Get Categories by their name like the value.
    @GetMapping("/api/categories/by_name_containing")
    public List<Categories> getCategoriesByName(@RequestParam("name") String name) {
        return this.categoriesRepository.findByCategoryNameContainingIgnoreCase(name);
    }

    // Get categories by their description like the value.
    @GetMapping("/api/categories/by_description")
    public List<Categories> getCategoriesByDescription(@RequestParam("description") String description) {
        return this.categoriesRepository.findByCategoryDescriptionContaining(description);
    }

    @PutMapping("/api/categories/add")
    public void addCategory(@RequestBody Categories category) {
        categoriesRepository.save(category);
    }

    @DeleteMapping("/api/categories/delete/{id}")
    public void deleteCategoryById(@PathVariable("id") int id) {
        categoriesRepository.deleteById(id);
    }

    @DeleteMapping("/api/categories/delete")
    public void deleteCategory(@RequestBody Categories category) {
        categoriesRepository.delete(category);
    }

    // ! END CATEGORIES METHOD.
}
