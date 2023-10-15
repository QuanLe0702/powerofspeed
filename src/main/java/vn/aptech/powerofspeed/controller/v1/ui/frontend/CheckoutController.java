package vn.aptech.powerofspeed.controller.v1.ui.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.aptech.powerofspeed.dto.model.user.UserDto;
import vn.aptech.powerofspeed.model.cart.Cart;
import vn.aptech.powerofspeed.model.cart.CartManager;
import vn.aptech.powerofspeed.model.order.Order;
import vn.aptech.powerofspeed.model.order.PaymentMethod;
import vn.aptech.powerofspeed.model.order.StatusType;
import vn.aptech.powerofspeed.model.orderdetail.OrderDetail;
import vn.aptech.powerofspeed.model.products.Product;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.model.user.UserDetailsImpl;
import vn.aptech.powerofspeed.repository.order.OrderRepository;
import vn.aptech.powerofspeed.service.OrderDetailService;
import vn.aptech.powerofspeed.service.OrderService;
import vn.aptech.powerofspeed.service.ProductService;
import vn.aptech.powerofspeed.service.UserService;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.Constants;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {
    
    //paypal client id, client secret
    private String clientId = "AZMw53e1HHA82mL3hPdA9Qptoj8A_XGXMFJoAfKTNczoeqNh2NAzqZdp_s1GLyxw7LPNlbo9Fx_ps_NB";
    private String clientSecret = "ELYuH2FYPItQgymwAAuIMj0eob4zSeXHiqiVwROfrNz_-e7ldkXGF-UWX50Y0ALZu2MUmx87RQrkksUS";

    @Autowired
    private CartManager cartManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/checkout")
    public String viewCheckout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if(email!= "anonymousUser") {
            UserDto currentUser = userService.findByEmail(email);
            model.addAttribute("currentUser", currentUser);
        }
        model.addAttribute("order", new Order());
        return "frontend/layout/pages/checkout";
    }
    

    @RequestMapping(value = "/doCheckout", method = RequestMethod.POST)
    public String doCheckout(Model model,HttpSession session,@ModelAttribute("order") Order order) {

        Cart cart = cartManager.getCart(session);
        order.setStatus(true);
        order.setAmount(cart.getTotal());
        order.setStatusType(StatusType.Confirm);

        if (order.getPaymentMethod() == PaymentMethod.Paypal) {
            try {
                // Set up PayPal API context
                APIContext apiContext = new APIContext(clientId, clientSecret, Constants.SANDBOX);

                // Create a Payment object
                Payment payment = new Payment();
                payment.setIntent("sale");

                // Set payer details
                Payer payer = new Payer();
                payer.setPaymentMethod("paypal");
                payment.setPayer(payer);

                // Add transaction details
                Transaction transaction = new Transaction();
                Amount amount = new Amount();
                amount.setCurrency("USD");
                amount.setTotal(String.valueOf(order.getAmount())); // Use the order total
                transaction.setAmount(amount);

                // Set the return URLs for success and cancel
                RedirectUrls redirectUrls = new RedirectUrls();
                redirectUrls.setReturnUrl("http://localhost:8080/paypal/success"); // Set your return URL
                redirectUrls.setCancelUrl("http://localhost:8080/paypal/cancel"); // Set your cancel URL
                payment.setRedirectUrls(redirectUrls);

                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                payment.setTransactions(transactions);

                // Create the payment and get the approval URL
                Payment createdPayment = payment.create(apiContext);
                String approvalUrl = null;
                for (Links link : createdPayment.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        approvalUrl = link.getHref();
                        break;
                    }
                }
                //update cart and order before change page?
                // orderService.save(order);
                // for(int i=0; i<cart.getItems().size(); i++){

                //     OrderDetail orderDetail = new OrderDetail();
                //     orderDetail.setOrder(order);
                //     orderDetail.setProduct(cart.getItems().get(i).getProduct());
                //     orderDetail.setPrice(cart.getItems().get(i).getProduct().getSavePrice());
                //     orderDetail.setQuantity(cart.getItems().get(i).getQuantity());
                //     orderDetail.setStatus(true);

                //     Product product = productService.findPk(cart.getItems().get(i).getProduct().getId());
                //     int quantityTotal = product.getStock() - cart.getItems().get(i).getQuantity();
                //     product.setStock(quantityTotal);
                //     productService.update(product);
                //     orderDetailService.save(orderDetail);
                // }
                // cartManager.removeCart(session);
                // model.addAttribute("orderTest", orderRepository.getNextId());
                // Redirect the user to the PayPal payment page for approval
                return "redirect:" + approvalUrl;
            } catch (PayPalRESTException e) {
                // Handle PayPal API errors
                System.err.println("PayPal API Error: " + e.getMessage());
                e.printStackTrace();
                // You can add error handling logic here
                return "frontend/layout/pages/paypal/cancel";
            }
        }

        orderService.save(order);
        for(int i=0; i<cart.getItems().size(); i++){

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cart.getItems().get(i).getProduct());
            orderDetail.setPrice(cart.getItems().get(i).getProduct().getSavePrice());
            orderDetail.setQuantity(cart.getItems().get(i).getQuantity());
            orderDetail.setStatus(true);

            Product product = productService.findPk(cart.getItems().get(i).getProduct().getId());
            int quantityTotal = product.getStock() - cart.getItems().get(i).getQuantity();
            product.setStock(quantityTotal);
            productService.update(product);
            orderDetailService.save(orderDetail);
        }
        cartManager.removeCart(session);
        model.addAttribute("orderTest", orderRepository.getNextId());
        return "frontend/layout/pages/success";
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        APIContext apiContext = new APIContext(clientId, clientSecret, Constants.SANDBOX);       
		Payment payment = Payment.get(apiContext, paymentId);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/paypal/cancel")
    public String cancelPay() {
        return "frontend/layout/pages/paypal/cancel";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/paypal/success")
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model,HttpSession session,@ModelAttribute("order") Order order, Authentication authentication){
		try {
			Payment payment = executePayment(paymentId, payerId);
            boolean isApproved = payment.getState().equals("approved");

			    // updateCheckoutAndDeleteCart(payment, isApproved);
                if(isApproved){
                    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                    String username = userDetails.getUsername();
                    User user = userService.findUserByEmail(username);
                    Cart cart = cartManager.getCart(session);
                    order.setStatus(true);
                    order.setAmount(cart.getTotal());
                    order.setStatusType(StatusType.Confirm);
                    order.setAddress(user.getAddress().getAddress());
                    order.setCity(user.getAddress().getCity());
                    order.setCountry(user.getAddress().getCountry());
                    order.setFirstName(user.getFirstName());
                    order.setLastName(user.getLastName());
                    order.setPaymentMethod(PaymentMethod.Paypal);
                    order.setPhoneNumber(user.getPhoneNumber());
                    order.setPostalCode(user.getAddress().getPostalCode());
                    order.setStateOrRegion(user.getAddress().getStateOrRegion());
                    order.setEmail(user.getEmail());
                    orderService.save(order);
                    for(int i=0; i<cart.getItems().size(); i++){

                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrder(order);
                        orderDetail.setProduct(cart.getItems().get(i).getProduct());
                        orderDetail.setPrice(cart.getItems().get(i).getProduct().getSavePrice());
                        orderDetail.setQuantity(cart.getItems().get(i).getQuantity());
                        orderDetail.setStatus(true);

                        Product product = productService.findPk(cart.getItems().get(i).getProduct().getId());
                        int quantityTotal = product.getStock() - cart.getItems().get(i).getQuantity();
                        product.setStock(quantityTotal);
                        productService.update(product);
                        orderDetailService.save(orderDetail);
                    }
                    cartManager.removeCart(session);
                    model.addAttribute("orderTest", orderRepository.getNextId());
                    return ("frontend/layout/pages/success");
                }
            
		} catch (PayPalRESTException e) {
            System.err.println("PayPal API Error: " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/";
	}

    // public void updateCheckoutAndDeleteCart(Payment payment, boolean isApproved){
    //     Order order = orderRepository.findById(Long.valueOf(payment.getTransactions().get(0).getCustom())).get();
    //     if (isApproved) {
    //         order.setStatus(true);
    //         order.setAmount(order.getAmount());
    //         order.setStatusType(StatusType.Confirm);
    //         orderService.save(order);
    //     orderRepository.save(order);
    // }
    // }
}
