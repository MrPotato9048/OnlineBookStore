package hkmu.comps380f.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="checkoutOrder")
public class CheckoutModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "checkoutId")
    private List<ShoppingCartItem> shoppingCartItems;
    private double totalPrice;
    private Date checkoutDate;

    public CheckoutModel() {
    }

    public CheckoutModel(String username, List<ShoppingCartItem> shoppingCartItems, double totalPrice, Date checkoutDate) {
        this.username = username;
        this.shoppingCartItems = shoppingCartItems;
        this.totalPrice = totalPrice;
        this.checkoutDate = checkoutDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}