package vn.aptech.powerofspeed.service;

import vn.aptech.powerofspeed.model.subcategory.Subcategory;

import java.util.List;

public interface SubcategoryService {
    List<Subcategory> findAllSubcat();
    void create(Subcategory subcategory);
    void update(Subcategory subcategory);
    Subcategory findPk(int id);
    Subcategory findBySlug(String slug);
    boolean checkSlugExists(String slug);
    void delete(int id);
}
