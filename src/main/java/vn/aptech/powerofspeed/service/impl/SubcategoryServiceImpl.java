package vn.aptech.powerofspeed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.repository.subcategory.SubcategoryRepository;
import vn.aptech.powerofspeed.service.SubcategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    SubcategoryRepository subcategoryRepo;

    @Override
    public List<Subcategory> findAllSubcat() {
        List<Subcategory> result = new ArrayList<>();
        Iterable<Subcategory> listCat = subcategoryRepo.findAll();
        for (Subcategory sub : listCat) {

            result.add(sub);
        }
        return result;

    }

    @Override
    public void create(Subcategory subcategory) {
        subcategoryRepo.save(subcategory);
    }

    @Override
    public void update(Subcategory subcategory) {
        subcategoryRepo.save(subcategory);
    }

    @Override
    public Subcategory findPk(int id) {
        try {
            Subcategory subcategory = subcategoryRepo.findById(id).get();

            return subcategory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Subcategory findBySlug(String slug) {
        return subcategoryRepo.findBySlug(slug);
    }

    @Override
    public boolean checkSlugExists(String slug) {
        if (subcategoryRepo.findBySlug(slug) != null) {
            return true;
        }

        return false;
    }

    @Override
    public void delete(int id) {
        Subcategory subcategory = subcategoryRepo.findById(id).get();
        subcategoryRepo.delete(subcategory);

    }
}
