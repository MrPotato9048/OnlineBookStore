package hkmu.comps380f.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ShoppingCartId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private AppUser appUser;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public double TotalPrice;

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public Long getShoppingCartId() {
        return ShoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        ShoppingCartId = shoppingCartId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
        shoppingCartItem.setShoppingCart(this);
    }

    public void removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.remove(shoppingCartItem);
        shoppingCartItem.setShoppingCart(null);
    }
}