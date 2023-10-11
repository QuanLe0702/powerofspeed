package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.powerofspeed.controller.v1.command.ProductStoreFormCommand;
import vn.aptech.powerofspeed.controller.v1.command.ProductUpdateFormCommand;
import vn.aptech.powerofspeed.dto.mapper.UserMapper;
import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.category.Category;
import vn.aptech.powerofspeed.model.images.Image;
import vn.aptech.powerofspeed.model.products.BidDetail;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.products.ProductType;
import vn.aptech.powerofspeed.model.subcategory.Subcategory;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.service.*;
import vn.aptech.powerofspeed.util.FileUtil;
import vn.aptech.powerofspeed.util.RandomStringUtil;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/product")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class ProductController {

    private final String PRODUCTS_IMAGE_PATH = "backend/dist/img/products";

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private BidDetailService bidDetailService;

    @Autowired
    private CategoryService categoryService;

    // SHOW ALL
    @RequestMapping(value = {"", "/", "index"}, method =RequestMethod.GET)
    public String product(Model model) {
        List<Product> products = productService.findAllPro();
        model.addAttribute("products", products);
        return "backend/layout/pages/product/index";
    }

    //Show image list of product
    @RequestMapping(value = "/imageList/{productId}")
    public String displayImageList(Model model, @PathVariable("productId") Long id){
        Product product = productService.findPk(id);
        model.addAttribute("product", product);
        return "backend/layout/pages/image/index";
    }


    @RequestMapping(value = "/doDefaultImage/{idImage}", method = RequestMethod.GET)
    public String doDefaultImage(@PathVariable("idImage") String id){
        Optional<Image> image = imageService.findPk(Integer.parseInt(id));
        Product product = productService.findPk(image.get().getProduct().getId());
        for (Image image1 : product.getImagesCollection()) {
            if (image1.getImageId() == Integer.parseInt(id)) {
                image1.setMainImage(true);
            } else {
                image1.setMainImage(false);
            }
        }

        productService.create(product);
        return "redirect:/admin/product/imageList/" + product.getId();
    }

    //create image of product

    @RequestMapping(value = "/createImages/{productId}", method = RequestMethod.GET)
    public String createImages(Model model, @PathVariable("productId") Long id){
        model.addAttribute("productId", id);
        model.addAttribute("image", new Image());
        return "backend/layout/pages/image/create";
    }

    @RequestMapping(value = "/doCreateImage", method = RequestMethod.POST)
    public String doCreateImage(@ModelAttribute("image") Image image, RedirectAttributes ra, @RequestParam("files") MultipartFile[] files, @RequestParam("productId") Long id) throws IOException{
        Product product = productService.findPk(id);
        UserDto user = userService.findById(Long.parseLong("1"));

        for (MultipartFile file : files) {
            String imageName = FileUtil.UploadedFile(file, PRODUCTS_IMAGE_PATH);
            Image storeImage = new Image();
            storeImage.setName(imageName);
            storeImage.setDescription(image.getDescription());
            storeImage.setAboutId(1);
            storeImage.setBlogId(1);
            storeImage.setUserId(new ModelMapper().map(user, User.class));
            storeImage.setProduct(product);
            imageService.saveImage(storeImage);
        }
        return "redirect:/admin/product/imageList/" + product.getId();
    }



    //CREATE - GET

    @RequestMapping(value= "/create" , method = RequestMethod.GET)
    public String create(Model model) {
        ProductStoreFormCommand productStoreFormCommand = new ProductStoreFormCommand();
        List<Subcategory> subcategories = subcategoryService.findAllSubcat();
        productStoreFormCommand.setSubcategories(subcategories);

        model.addAttribute("productStoreFormCommand", productStoreFormCommand);
        return "backend/layout/pages/product/create";
    }


    //CREATE - POST

    @RequestMapping(value= "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("productStoreFormCommand") ProductStoreFormCommand productStoreFormCommand, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Product checkSlugExists = productService.findBySlug(RandomStringUtil.makeSlug(productStoreFormCommand.getProductName()));
        Product checkSkuExists = productService.findBySKU(productStoreFormCommand.getSku());
        if (checkSlugExists != null) {
            redirectAttributes.addFlashAttribute("slugErr", "Product name is exists!");
            return "redirect:/admin/product/create";
        }

        if (checkSkuExists != null) {
            redirectAttributes.addFlashAttribute("skuErr", "SKU is exists!");
            return "redirect:/admin/product/create";
        }

        if (productStoreFormCommand.getUnitPrice() <= 0) {
            redirectAttributes.addFlashAttribute("unitPrice", "Unit price greater than 0");
            return "redirect:/admin/product/create";
        }

        if (bindingResult.hasErrors()) {
            return "backend/layout/pages/product/create";
        } else {
            Product product = storedProduct(productStoreFormCommand);
            return "redirect:/admin/product/imageList/" + product.getId();
        }
    }

    private Product storedProduct(ProductStoreFormCommand productStoreFormCommand) {
        Product product = new Product();
        Subcategory subcategory = subcategoryService.findPk(Integer.parseInt(productStoreFormCommand.getCategory()));

        if (productStoreFormCommand.getProductType() == ProductType.Auction) {

            product.setSlug(productStoreFormCommand.getSlug());
            product.setSku(productStoreFormCommand.getSku());
            product.setProductName(productStoreFormCommand.getProductName());
            product.setProductDescription(productStoreFormCommand.getProductDescription());
            product.setProductContent(productStoreFormCommand.getProductContent());
            product.setUnitPrice(productStoreFormCommand.getUnitPrice());
            product.setSavePrice(productStoreFormCommand.getSavePrice());
            product.setUnitWeight(productStoreFormCommand.getUnitWeight());
            product.setStock(productStoreFormCommand.getStock());
            product.setProductType(productStoreFormCommand.getProductType());
            BidDetail bidDetail = bidDetailService.stored(new BidDetail()
                    .setBidIncrement(productStoreFormCommand.getBidIncrement())
                    .setAuctionStart(productStoreFormCommand.getAuctionStart())
                    .setAuctionEnd(productStoreFormCommand.getAuctionEnd()));
            product.setBidDetail(bidDetail);
            product.setSubcategory(subcategory);
        } else {
            product.setSlug(productStoreFormCommand.getSlug());
            product.setSku(productStoreFormCommand.getSku());
            product.setProductName(productStoreFormCommand.getProductName());
            product.setProductDescription(productStoreFormCommand.getProductDescription());
            product.setProductContent(productStoreFormCommand.getProductContent());
            product.setUnitPrice(productStoreFormCommand.getUnitPrice());
            product.setSavePrice(productStoreFormCommand.getSavePrice());
            product.setUnitWeight(productStoreFormCommand.getUnitWeight());
            product.setStock(productStoreFormCommand.getStock());
            product.setProductType(productStoreFormCommand.getProductType());
            product.setSubcategory(subcategory);
        }

//        if (productStoreFormCommand.getProductType() == ProductType.Auction) {
//            product.setProductType(ProductType.Auction);
//        } else {
//            product.setProductType(ProductType.Sell);
//        }

        return productService.create(product);
    }


    //UPDATE PRODUCT - GET
    @RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("productId") Long productId) {
        Product product = productService.findPk(productId);
        if(product != null) {
            ProductUpdateFormCommand productUpdateFormCommand = new ProductUpdateFormCommand();
            productUpdateFormCommand.setId(product.getId());
            productUpdateFormCommand.setSlug(product.getSlug());
            productUpdateFormCommand.setSku(product.getSku());
            productUpdateFormCommand.setProductName(product.getProductName());
            productUpdateFormCommand.setProductDescription(product.getProductDescription());
            productUpdateFormCommand.setProductContent(product.getProductContent());
            productUpdateFormCommand.setUnitPrice(product.getUnitPrice());
            productUpdateFormCommand.setSavePrice(product.getSavePrice());
            productUpdateFormCommand.setUnitWeight(product.getUnitWeight());
            productUpdateFormCommand.setStock(product.getStock());
            productUpdateFormCommand.setProductType(product.getProductType());
            productUpdateFormCommand.setStatus(product.isStatus());
            productUpdateFormCommand.setSubcategories(subcategoryService.findAllSubcat());

            if (product.getBidDetail() != null) {
                productUpdateFormCommand.setBidIncrement(product.getBidDetail().getBidIncrement());
                productUpdateFormCommand.setAuctionStart(product.getBidDetail().getAuctionStart());
                productUpdateFormCommand.setAuctionEnd(product.getBidDetail().getAuctionEnd());
                productUpdateFormCommand.setAuction(true);
            }

            model.addAttribute("productUpdateFormCommand", productUpdateFormCommand);
        }
        return "backend/layout/pages/product/update";
    }


    //UPDATE IMAGE PRODUCT - GET
    @RequestMapping(value = "/editImage/{imageId}", method = RequestMethod.GET)
    public String editImage(Model model, @PathVariable("imageId") int id){
        Optional<Image> image = imageService.findPk(id);
        if (image.isPresent()) {

            model.addAttribute("image", image.get());
        }
        return "backend/layout/pages/image/edit";
    }

    //UPDATE IMAGE PRODUCT - POST

    @RequestMapping(value = "/doUpdateImage", method = RequestMethod.POST)
    public String updateImage(@Valid @ModelAttribute("image") Image imageUpdate, @RequestParam("filePicture") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {

        Optional<Image> imageOrigin = imageService.findPk(imageUpdate.getImageId());

        if (imageOrigin.isPresent()) {
            String pictureOrigin =  imageOrigin.get().getName();

            String uniqueFileName = FileUtil.UploadedFile(multipartFile, PRODUCTS_IMAGE_PATH);

            imageOrigin.get().setDescription(imageUpdate.getDescription());
            imageOrigin.get().setName(uniqueFileName);

            imageService.saveImage(imageOrigin.get());
            FileUtil.DeletedFile(PRODUCTS_IMAGE_PATH, pictureOrigin);


            redirectAttributes.addFlashAttribute("success", "Update image with id " + imageOrigin.get().getImageId() + " successfully");
            return "redirect:/admin/product/imageList/" + imageOrigin.get().getProduct().getId().toString();
        }


        redirectAttributes.addFlashAttribute("error", "Image not found with id " + imageUpdate.getImageId() + ". Please check id again!");
        return "redirect:/admin/product/imageList/" + imageUpdate.getProduct().getId().toString();
    }

    //UPDATE - POST

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model,
                           @ModelAttribute("productUpdateFormCommand") ProductUpdateFormCommand productUpdateFormCommand) {

        Product product = productService.findPk(productUpdateFormCommand.getId());
        Subcategory subcategory = subcategoryService.findPk(productUpdateFormCommand.getCategory());

        if (product.getProductType() == ProductType.Auction) {

            product.setSlug(productUpdateFormCommand.getSlug());
            product.setSku(productUpdateFormCommand.getSku());
            product.setProductName(productUpdateFormCommand.getProductName());
            product.setProductDescription(productUpdateFormCommand.getProductDescription());
            product.setProductContent(productUpdateFormCommand.getProductContent());
            product.setUnitPrice(productUpdateFormCommand.getUnitPrice());
            product.setSavePrice(productUpdateFormCommand.getSavePrice());
            product.setUnitWeight(productUpdateFormCommand.getUnitWeight());
            product.setStock(productUpdateFormCommand.getStock());
            product.setProductType(productUpdateFormCommand.getProductType());
            BidDetail bidDetail = bidDetailService.stored(new BidDetail()
                    .setBidIncrement(productUpdateFormCommand.getBidIncrement())
                    .setAuctionStart(productUpdateFormCommand.getAuctionStart())
                    .setAuctionEnd(productUpdateFormCommand.getAuctionEnd()));
            product.setBidDetail(bidDetail);
            product.setSubcategory(subcategory);
        } else {
            product.setSlug(productUpdateFormCommand.getSlug());
            product.setSku(productUpdateFormCommand.getSku());
            product.setProductName(productUpdateFormCommand.getProductName());
            product.setProductDescription(productUpdateFormCommand.getProductDescription());
            product.setProductContent(productUpdateFormCommand.getProductContent());
            product.setUnitPrice(productUpdateFormCommand.getUnitPrice());
            product.setSavePrice(productUpdateFormCommand.getSavePrice());
            product.setUnitWeight(productUpdateFormCommand.getUnitWeight());
            product.setStock(productUpdateFormCommand.getStock());
            product.setProductType(productUpdateFormCommand.getProductType());
            product.setSubcategory(subcategory);
        }

        productService.update(product);
        return "redirect:/admin/product";

    }

    //DELETE
    @RequestMapping(value = "/delete/{idDelete}", method = RequestMethod.GET)
    public String deleteProduct(RedirectAttributes redirectAttributes, @PathVariable("idDelete") String id) {
        Product product = productService.findPk(Long.parseLong(id));

        productService.delete(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("success", "Deleted product " + product.getProductName() + " successfully");

        return "redirect:/admin/product";
    }

    //DELETE IMAGE
    @RequestMapping(value = "/deleteImage/{imageId}/product/{productId}", method = RequestMethod.GET)
    public String deleteImage(RedirectAttributes redirectAttributes, @PathVariable("imageId") int idDelete, @PathVariable("productId") Long productId) {

        Optional<Image> image = imageService.findPk(idDelete);


        if (image.get().isMainImage()) {
            redirectAttributes.addFlashAttribute("error", "Default is not allow to be deleted!");
            return "redirect:/admin/product/imageList/" + productId.toString();
        }

        imageService.delete(image.get());

        redirectAttributes.addFlashAttribute("success", "Deleted successfully");

        return "redirect:/admin/product/imageList/" + productId.toString();
    }
}



