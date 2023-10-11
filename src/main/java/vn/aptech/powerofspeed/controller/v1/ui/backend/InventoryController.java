package  vn.aptech.powerofspeed.controller.v1.ui.backend;


import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import  vn.aptech.powerofspeed.controller.v1.request.ProductInventoryRequest;
import  vn.aptech.powerofspeed.model.inventory.Inventory;
import  vn.aptech.powerofspeed.model.products.Product;
import  vn.aptech.powerofspeed.model.products.ProductType;
import  vn.aptech.powerofspeed.model.review.Review;
import  vn.aptech.powerofspeed.model.subcategory.Subcategory;
import  vn.aptech.powerofspeed.model.supplier.Supplier;
import  vn.aptech.powerofspeed.service.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "admin/inventory")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;


    // SHOW ALL
    @RequestMapping(value = {"", "/", "index"})
    public String inventory(Model model) {

        model.addAttribute("inventory",inventoryService.findAllInventory());
        return "backend/layout/pages/inventory/index";
    }

    // Invoice detail of product
    @RequestMapping(value = "/inventoryDetailProduct/{productId}", method = RequestMethod.GET)
    public String inventoryDetailProduct(Model model, @PathVariable("productId") Long id){
        Product product = productService.findPk(id);
        model.addAttribute("inventoryProduct",  product);
        return "backend/layout/pages/inventory/inventoryDetail";
    }

    @RequestMapping(value = "/reportInvoice", method = RequestMethod.GET)
    public String reportInvoice(Model model){
//        Product product = productService.findPk(id);
//        model.addAttribute("inventoryProduct",  product);
        return "backend/layout/pages/inventory/inventoryReport";
    }

    @RequestMapping(value = "/printReportInvoice", method = RequestMethod.GET)
    public String printReportInvoice(Model model){
//        Product product = productService.findPk(id);
//        model.addAttribute("inventoryProduct",  product);
        return "backend/layout/pages/inventory/inventory-print";
    }

    //CREATE - GET

    @RequestMapping(value= "/createFormInvoiceDetail", method = RequestMethod.GET)
    public String displayCreateFormInvoiceDetail(Model model, @ModelAttribute("productInventoryRequest") ProductInventoryRequest productInventoryRequest) {
        Inventory inventory = new Inventory();
        List<Product> productSellerList = new ArrayList<>();
        List<Product> products = productService.findAllPro();
        List<Product> productTableList = new ArrayList<>();

        for (Product product : products) {
            if (product.getProductType() == ProductType.Sell) {
                productSellerList.add(product);
            }
        }

        if (productInventoryRequest.getProductIdList() != null) {
            for (String productId : productInventoryRequest.getProductIdList()) {
                Product product = productService.findPk(Long.parseLong(productId));
                productTableList.add(product);
            }

            productInventoryRequest.setProductInventorList(productTableList);
        }

        model.addAttribute("productInventoryRequest", productInventoryRequest);
        model.addAttribute("products", products);
        model.addAttribute("inventory",inventory);
        return "backend/layout/pages/inventory/create";
    }

    //CREATE - POST

    @RequestMapping(value= "/createInvoiceDetail", method = RequestMethod.POST)
    public String createNewInvoiceDetail(Model model,
                                @ModelAttribute("productInventoryRequest") ProductInventoryRequest productInventoryRequest) {
        for (Product productInventory : productInventoryRequest.getProductInventorList()) {
            Inventory inventory = new Inventory();
            Product product = productService.findPk(productInventory.getId());

            int totalStock = product.getStock() + productInventory.getQuantityInventory();

            inventory.setProduct(product);
            inventory.setStartingInventory(product.getStock());
            inventory.setQuantityReceived(productInventory.getQuantityInventory());
            inventory.setInventoryOnHand(totalStock);
            product.setStock(totalStock);

            inventoryService.create(inventory);
            productService.update(product);
        }

        return "redirect:/admin/inventory/index";
    }


    //UPDATE - GET
//    @RequestMapping(value = "/displayUpdateProduct/{idUpdate}")
//    public String displayUpdateProduct(Model model, @PathVariable("idUpdate") String id) {
//        Product product = productService.findPk(Long.parseLong(id));
//        if(product != null) {
//            List<Subcategory> subcategoryList = subcategoryService.findAllSubcat();
//            model.addAttribute("subcategory",subcategoryList);
//            model.addAttribute("product", product);
//        }
//        return "backend/layout/pages/product/update";
//    }

    //UPDATE - POST

//    @RequestMapping(value = "/doUpdateProduct", method = RequestMethod.POST)
//    public String doUpdate(Model model,
//                           @ModelAttribute("product") Product product) {
//        Subcategory subcategory = subcategoryService.findPk(product.getSubcategory().getSubcatId());
//        product.setSubcategory(subcategory);
//        productService.update(product);
//        return "redirect:/admin/products";
//
//    }

    //DELETE
//    @RequestMapping(value = "/delete/{idDelete}", method = RequestMethod.GET)
//    public String deleteProduct(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) {
//        Product product = productService.findPk(Long.parseLong(id));
//
//        productService.delete(Long.parseLong(id));
//        redirectAttributes.addFlashAttribute("success", "Deleted product " + product.getName() + " successfully");
//
//        return "redirect:/admin/products";
//    }



}
