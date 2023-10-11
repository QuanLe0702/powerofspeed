package  vn.aptech.powerofspeed.controller.v1.ui.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import  vn.aptech.powerofspeed.model.category.Category;
import  vn.aptech.powerofspeed.model.products.Product;
import  vn.aptech.powerofspeed.model.subcategory.Subcategory;
import  vn.aptech.powerofspeed.service.CategoryService;
import  vn.aptech.powerofspeed.service.SubcategoryService;
import  vn.aptech.powerofspeed.util.RandomStringUtil;


import java.util.List;

@Controller
@RequestMapping(value = "admin/subcategory")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subService;

    @Autowired
    private CategoryService categoryService;

    // SHOW ALL
    @RequestMapping(value = {"", "/", "index"})
    public String subcategory(Model model) {
        model.addAttribute("subcategories",subService.findAllSubcat());
        return "backend/layout/pages/subcategory/index";
    }

    //CREATE - GET

    @RequestMapping(value= "/createFormSubcategory")
    public String displayCreateFormSubcat(Model model) {

        Subcategory subcategory = new Subcategory();
        List<Category> categoryList = categoryService.findAllCat();
        model.addAttribute("categories", categoryList);
        model.addAttribute("subcategory",subcategory);
        return "backend/layout/pages/subcategory/create";
    }


    //CREATE - POST

    @RequestMapping(value= "/createSubcategory", method = RequestMethod.POST)
    public String createSubcategory(Model model,
                                    @ModelAttribute("subcategory") Subcategory subcategory) {
        String uniqueSlug = RandomStringUtil.makeSlug(subcategory.getSubName());
        Category category = categoryService.findPk(subcategory.getCategory().getCategoryID());
        subcategory.setCategory(category);
        subcategory.setSlug(uniqueSlug);
        subService.create(subcategory);
        return "redirect:/admin/category";
    }


    //UPDATE - GET
    @RequestMapping(value = "/displayUpdateSubcategory/{idUpdate}")
    public String displayUpdateSubcategory(Model model, @PathVariable("idUpdate") String id) {
        Subcategory subcategory = subService.findPk(Integer.parseInt(id));
        if(subcategory != null) {
            List<Category> categoryList = categoryService.findAllCat();
            model.addAttribute("categories", categoryList);
            model.addAttribute("subcategory",subcategory);
        }
        return "backend/layout/pages/subcategory/update";
    }

    //UPDATE - POST

    @RequestMapping(value = "/doUpdateSubcategory", method = RequestMethod.POST)
    public String doUpdate(Model model,
                           @ModelAttribute("subcategory") Subcategory subcategory) {
        Subcategory subcategoryUpdate = subService.findPk(subcategory.getSubcatId());
        Category category = categoryService.findPk(subcategory.getCategory().getCategoryID());
        subcategoryUpdate.setSubName(subcategory.getSubName());
        subcategoryUpdate.setSlug(RandomStringUtil.makeSlug(subcategory.getSubName()));
        subcategoryUpdate.setCategory(category);
        subService.update(subcategoryUpdate);
        return "redirect:/admin/category";

    }

    //DELETE
    @RequestMapping(value = "/delete/{idDelete}", method = RequestMethod.GET)
    public String deleteSubCategory(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) {
        Subcategory subcategory = subService.findPk(Integer.parseInt(id));

        subService.delete(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("success", "Deleted subcategory " + subcategory.getSubName() + " successfully");

        return "redirect:/admin/category";
    }

}

