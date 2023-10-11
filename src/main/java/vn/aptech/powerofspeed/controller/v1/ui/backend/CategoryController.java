package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.service.CategoryService;
import vn.aptech.powerofspeed.util.RandomStringUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "admin/category")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // SHOW ALL
    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String category(Model model) {
        model.addAttribute("categories",categoryService.findAllCat());
        return "backend/layout/pages/category/index";
    }


    //CREATE - GET

    @RequestMapping(value= "/createForm", method = RequestMethod.GET)
    public String displayCreateForm(Model model) {
        Category c = new Category();
        model.addAttribute("category",c);
        return "backend/layout/pages/category/create";
    }


    //CREATE - POST
    //Cua anh Hoang ga
//    @RequestMapping(value= "/createCat", method = RequestMethod.POST)
//    public String createBook(Model model,
//                             @ModelAttribute("category") Category cat,
//                             BindingResult result,
//                             final RedirectAttributes redirectAttributes ) {
//        categoryService.create(cat);
//        return "redirect:/admin/category";
//    }

    //cua thang em lao
        @RequestMapping(value= "/create", method = RequestMethod.POST)
    public String create(@Valid Category model,
                             @ModelAttribute("category") Category cat,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        String uniqueSlug = RandomStringUtil.makeSlug(cat.getName());
        if (!categoryService.checkSlugExists(uniqueSlug)) {

            if (result.hasErrors()) {
                return "backend/layout/pages/category/create";
            }

            redirectAttributes.addAttribute("name", model.getName());
            redirectAttributes.addAttribute("slug", model.getSlug());
            cat.setSlug(uniqueSlug);
            Category saveCate = categoryService.create(cat);
        }

        return "redirect:/admin/category/index";
    }

    //UPDATE - GET
    @RequestMapping(value = "/displayUpdate/{idUpdate}")
    public String displayUpdate(Model model, @PathVariable("idUpdate") String id) {
        Category category = categoryService.findPk(Integer.parseInt(id));
        if(category != null) {
            model.addAttribute("category",category);
        }
        return "backend/layout/pages/category/update";
    }

    //UPDATE - POST

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    public String doUpdate(Model model,
                           @ModelAttribute("category") Category cat) {

        categoryService.update(cat);
        return "redirect:/admin/category";

    }

    //DELETE
    @RequestMapping(value = "/delete/{idDelete}", method = RequestMethod.GET)
    public String deleteCategory(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) {
        Category category = categoryService.findPk(Integer.parseInt(id));

        if (category.getSubcategories().stream().count() > 0) {
            redirectAttributes.addFlashAttribute("error", "There are many subcategories inside. Remove is not allowed");
        } else {
            categoryService.delete(Integer.parseInt(id));
            redirectAttributes.addFlashAttribute("success", "Deleted category " + category.getName() + " successfully");
        }

        return "redirect:/admin/category";
    }


}
