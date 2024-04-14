package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppingCartItems")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ShoppingCartItemId;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shoppingCartId", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "checkoutId")
    private CheckoutModel checkoutModel;

    public long getShoppingCartItemId() {
        return ShoppingCartItemId;
    }

    public void setShoppingCartItemId(long shoppingCartItemId) {
        ShoppingCartItemId = shoppingCartItemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getTotalPrice() {
        return this.quantity * this.book.getPrice();
    }

    public CheckoutModel getCheckoutModel() {
        return checkoutModel;
    }

    public void setCheckoutModel(CheckoutModel checkoutModel) {
        this.checkoutModel = checkoutModel;
    }
}