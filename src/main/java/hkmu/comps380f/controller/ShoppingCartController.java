package hkmu.comps380f.controller;
import hkmu.comps380f.dao.CheckoutRepository;
import hkmu.comps380f.dao.OrderService;
import hkmu.comps380f.dao.ShoppingCartItemRepository;
import hkmu.comps380f.dao.ShoppingCartService;
import hkmu.comps380f.model.Order;
import hkmu.comps380f.model.ShoppingCart;
import hkmu.comps380f.model.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hkmu.comps380f.model.CheckoutModel;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepo;
    @Autowired
    private CheckoutRepository checkoutRepo;

    @GetMapping
    public String getShoppingCart(Model model, Principal principal) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(principal.getName());
        List<Long> itemTotalPrices = shoppingCart.getShoppingCartItems().stream()
            .map(item -> item.getBook().getPrice() * item.getQuantity())
            .collect(Collectors.toList());
        System.out.println("Item total prices: " + itemTotalPrices);
        double totalPrice = itemTotalPrices.stream().mapToDouble(Double::valueOf).sum();
        System.out.println("Total price: " + totalPrice);
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("itemTotalPrices", itemTotalPrices);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("principal", principal);
        return "shoppingCart";
    }

    @PostMapping("/add/{bookId}")
    public String addShoppingCartItem(@PathVariable("bookId") long bookId, @RequestParam("quantity") int quantity, Principal principal) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(principal.getName());
        ShoppingCartItem existingItem = shoppingCart.getShoppingCartItems().stream()
            .filter(item -> item.getBook().getId() == bookId)
            .findFirst()
            .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            shoppingCartItemRepo.save(existingItem);
        } else {
            shoppingCartService.addShoppingCartItem(principal.getName(), bookId, quantity);
        }

        return "redirect:/shoppingCart";
    }
    
    @PostMapping("/remove/{bookId}")
    public String removeShoppingCartItem(@PathVariable("bookId") long bookId, Principal principal) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(principal.getName());
        ShoppingCartItem existingItem = shoppingCart.getShoppingCartItems().stream()
            .filter(item -> item.getBook().getId() == bookId)
            .findFirst()
            .orElse(null);
    
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() - 1;
            if (newQuantity > 0) {
                existingItem.setQuantity(newQuantity);
                shoppingCartItemRepo.save(existingItem);
            } else {
                shoppingCartService.removeShoppingCartItem(principal.getName(), bookId);
            }
        }
    
        return "redirect:/shoppingCart";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model, Principal principal) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(principal.getName());
        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        double totalPrice = shoppingCartItems.stream().mapToDouble(ShoppingCartItem::getTotalPrice).sum();
    
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("principal", principal);
        return "checkout";
    }
    
    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(principal.getName());
        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        double totalPrice = shoppingCartItems.stream().mapToDouble(ShoppingCartItem::getTotalPrice).sum();
        List<ShoppingCartItem> checkoutItems = new ArrayList<>(shoppingCartItems);
        CheckoutModel checkoutModel = new CheckoutModel(principal.getName(), checkoutItems, totalPrice, new Date());
        orderService.createOrder(checkoutModel);
        shoppingCartService.clearShoppingCart(principal.getName());
        return "redirect:/orders";
    }

}
