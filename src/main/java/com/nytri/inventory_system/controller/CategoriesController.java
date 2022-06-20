package com.nytri.inventory_system.controller;


import com.nytri.inventory_system.Configuration;
import com.nytri.inventory_system.entity.Categories;
import com.nytri.inventory_system.repositories.CategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesRepository categoriesRepository;
    private final Logger logger =LoggerFactory.getLogger(this.getClass());

    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    // ! START CATEGORIES METHOD.

    @GetMapping("/all")
    public List<Categories> showAllCategories() {
        return (List<Categories>) categoriesRepository.findAll();
    }

    @GetMapping("/all/{page}")
    public List<Categories> showAllCategoriesAtPage(@PathVariable("page") Integer pageNum) {
        return categoriesRepository.findAll(PageRequest.of(pageNum, Configuration.categories_pagination_max_data)).getContent();
    }

    @GetMapping("/by_name")
    public Categories getCategoryByName(@RequestParam("name") String name) {
        return this.categoriesRepository.findByCategoryName(name);
    }

    // Get Categories by their name like the value.
    @GetMapping("/by_name_containing")
    public List<Categories> getCategoriesByName(@RequestParam("name") String name) {
        return this.categoriesRepository.findByCategoryNameContainingIgnoreCase(name);
    }

    // Get categories by their description like the value.
    @GetMapping("/by_description")
    public List<Categories> getCategoriesByDescription(@RequestParam("description") String description) {
        return this.categoriesRepository.findByCategoryDescriptionContaining(description);
    }

    @PutMapping("/add")
    public String addCategory(@RequestBody Categories category) {
        // Check if the category already exist, if it exists, then notify the user.
        if (categoriesRepository.findByCategoryName(category.getCategoryName()) != null) {
            logger.info("Category " + category.getCategoryName() + " already exist.");
            return "Category " + category.getCategoryName() + " already exist.";
        }

        logger.info("Category " + category.getCategoryName() + " was saved successfully.");
        categoriesRepository.save(category);
        return "Category " + category.getCategoryName() + " was saved successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryById(@PathVariable("id") int id) {
        logger.warn("Item with an ID of " + id + " was deleted.");
        categoriesRepository.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestBody Categories category) {
        logger.warn("Deleting a category with a name of " + category.getCategoryName());
        categoriesRepository.delete(category);
    }

    // ! END CATEGORIES METHOD.
}
