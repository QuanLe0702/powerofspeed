package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.service.OrderService;
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
}
