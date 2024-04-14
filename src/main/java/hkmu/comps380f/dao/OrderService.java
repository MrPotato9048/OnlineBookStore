package hkmu.comps380f.dao;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.CheckoutModel;
import hkmu.comps380f.model.Order;
import hkmu.comps380f.model.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import hkmu.comps380f.model.OrderItem; // Add this import statement

@Service

public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private AppUserRepository appUserRepo;

    public Order createOrder(CheckoutModel checkoutModel) {
        AppUser appUser = appUserRepo.findByUsername(checkoutModel.getUsername());
    
        Order order = new Order();
        order.setAppUser(appUser);
        order.setTotalPrice(checkoutModel.getTotalPrice());
        order.setCheckoutDate(checkoutModel.getCheckoutDate());
    
        order.setOrderItems(convertToOrderItems(checkoutModel.getShoppingCartItems(), order));
    
        order = orderRepo.save(order);
    
        return order;
    }

    private List<OrderItem> convertToOrderItems(List<ShoppingCartItem> shoppingCartItems, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(shoppingCartItem.getBook());
            orderItem.setQuantity(shoppingCartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public List<Order> getOrders(String username) {
        System.out.println("Processing getOrders. Username: " + username);
        AppUser appUser = appUserRepo.findByUsername(username);
    
        if (appUser != null) {
            return orderRepo.findByAppUser(appUser);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
