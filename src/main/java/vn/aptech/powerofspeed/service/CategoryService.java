package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.category.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCat();
    Category create(Category cat);
    void update(Category cat);
    Category findPk(int id);
    Category findBySlug(String slug);
    boolean checkSlugExists(String slug);
    void delete(int id);
}
