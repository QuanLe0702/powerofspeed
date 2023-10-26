package vn.aptech.powerofspeed.controller.v1.ui.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.model.order.StatusType;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.service.OrderService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/order")
@PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String product(Model model) {
        List<Order> orders = orderService.findAllOrder();
        model.addAttribute("orders", orders);
        return "backend/layout/pages/order/index";
    }

    // Order detail
    @RequestMapping(value = "/orderDetail/{orderId}", method = RequestMethod.GET)
    public String inventoryDetailProduct(Model model, @PathVariable("orderId") Long id){
        Optional<Order> order = orderService.findOrderById(id);
        order.ifPresent(orderDetail->model.addAttribute("orderDetail", orderDetail));
        return "backend/layout/pages/order/orderDetail";
    }

    @RequestMapping(value = "/updateStatus/{orderId}", method = RequestMethod.POST)
    public String updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") StatusType status) {
        Optional<Order> optionalOrder = orderService.findOrderById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatusType(status);
            orderService.update(order); // Update the order status
        } else {
            // Handle the case where the order is not found
        }

        return "redirect:/admin/order/orderDetail/" + orderId;
    }
}
