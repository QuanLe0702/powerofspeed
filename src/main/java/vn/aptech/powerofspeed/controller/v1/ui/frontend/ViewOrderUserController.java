package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.service.OrderService;

import java.util.Optional;

@Controller
public class ViewOrderUserController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "searchOrder")
    public String viewSearchOrder(){
        return "frontend/layout/pages/searchOrder";
    }

    @RequestMapping(value = "searchOrder", method = RequestMethod.POST)
    public String doSearchOrder(Model model, @RequestParam("id") long id){
        Optional<Order> orderSearch = orderService.findOrderById(id);
        orderSearch.ifPresent(order->model.addAttribute("order", order));
        return "frontend/layout/pages/searchOrder";
    }
}
