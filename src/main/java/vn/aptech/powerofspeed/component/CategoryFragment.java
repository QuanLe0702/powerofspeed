package vn.aptech.powerofspeed.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.service.CategoryService;

import java.util.List;

@Configuration
public class CategoryFragment {
    @Autowired
    private CategoryService categoryService;

    @Bean(name = "getCategoryList")
    public List<Category> getCategoryList(){
        List<Category> categoryList = categoryService.findAllCat();
        return categoryList;
    }
}
