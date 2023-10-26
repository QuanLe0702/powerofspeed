package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.service.OrderService;
import vn.aptech.powerofspeed.service.PdfGeneratorService;
import vn.aptech.powerofspeed.service.impl.OrderServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewOrderUserController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping(value = "orderHistory")
    public String viewSearchOrder(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Get the logged-in user's email

        List<Order> orders = getOrderByEmail(userEmail);
        model.addAttribute("orders", orders);
        return "frontend/layout/pages/orderHistory";
    }

    @RequestMapping(value = "searchOrder", method = RequestMethod.POST)
    public String doSearchOrder(Model model, @RequestParam("id") long id){
        Optional<Order> orderSearch = orderService.findOrderById(id);
        orderSearch.ifPresent(order->model.addAttribute("order", order));
        return "frontend/layout/pages/order/orderDetail";
    }

    // Order detail
    @RequestMapping(value = "order/orderDetail/{orderId}", method = RequestMethod.GET)
    public String inventoryDetailProduct(Model model, @PathVariable("orderId") Long id){
        Optional<Order> order = orderService.findOrderById(id);
        order.ifPresent(orderDetail->model.addAttribute("orderDetail", orderDetail));
        return "frontend/layout/pages/order/orderDetail";
    }

    public List<Order> getOrderByEmail(@RequestParam("email") String email){
        return orderServiceImpl.getOrderByEmail(email);
    }

    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.GET)
    public String removeOrder(@PathVariable("orderId") Long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/order/index";
    }

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping("/order/orderDetail/{orderId}/generatePdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable("orderId") Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        if (order.isPresent()) {
            Context context = new Context();
            context.setVariable("orderDetail", order.get());

            try {
                byte[] pdfBytes = pdfGeneratorService.generatePdfFromThymeleafTemplate("frontend/layout/pages/order/orderDetail", context);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("inline", "orderDetail.pdf");
                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } catch (Exception e) {
                // Handle the exception (e.g., log it or return an error response)
            }
        }
        return ResponseEntity.notFound().build();
    }
}
