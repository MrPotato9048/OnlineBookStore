package hkmu.comps380f.controller;
import hkmu.comps380f.dao.OrderService;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/orders")

public class OrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrders(Model model, Principal principal) {
        System.out.println("Processing getOrders. Principal: " + principal.getName());
        List<Order> orders = orderService.getOrders(principal.getName());
        model.addAttribute("orders", orders);
        model.addAttribute("principal", principal);
        return "orders";
    }
}
