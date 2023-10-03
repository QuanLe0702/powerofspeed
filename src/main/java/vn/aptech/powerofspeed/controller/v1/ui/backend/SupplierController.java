package vn.aptech.powerofspeed.controller.v1.ui.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.supplier.Supplier;
import vn.aptech.powerofspeed.service.CategoryService;
import vn.aptech.powerofspeed.service.SupplierService;

@Controller
@RequestMapping(value = "admin/supplier")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    // SHOW ALL
    @RequestMapping(value = {"", "/", "index"})
    public String supplier(Model model) {
        model.addAttribute("suppliers",supplierService.findAllSupplier());
        return "backend/layout/pages/supplier/index";
    }

    //CREATE - GET

    @RequestMapping(value= "/createFormSupplier")
    public String displayCreateForm(Model model) {
        Supplier s = new Supplier();
        model.addAttribute("supplier",s);
        return "backend/layout/pages/supplier/create";
    }


    //CREATE - POST

    @RequestMapping(value= "/createSupplier", method = RequestMethod.POST)
    public String createSupplier(Model model,
                             @ModelAttribute("supplier") Supplier supplier,
                             BindingResult result,
                             final RedirectAttributes redirectAttributes ) {
        supplierService.create(supplier);
        return "redirect:/admin/supplier";
    }

    //UPDATE - GET
    @RequestMapping(value = "/displayUpdateSupplier/{idUpdate}")
    public String displayUpdate(Model model, @PathVariable("idUpdate") String id) {
        Supplier supplier = supplierService.findPk(Long.parseLong(id));
        if(supplier != null) {
            model.addAttribute("supplier",supplier);
        }
        return "backend/layout/pages/supplier/update";
    }

    //UPDATE - POST

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    public String doUpdate(Model model,
                           @ModelAttribute("supplier") Supplier supplier) {

        supplierService.update(supplier);
        return "redirect:/admin/supplier";

    }

    //DELETE
    @RequestMapping(value = "/delete/{idDelete}", method = RequestMethod.GET)
    public String deleteCategory(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) {
        Supplier supplier = supplierService.findPk(Long.parseLong(id));


            supplierService.delete(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("success", "Deleted supplier " + supplier.getName() + " successfully");


        return "redirect:/admin/supplier";
    }

}
