package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long OrderItemId;

    @ManyToOne
    @JoinColumn(name ="bookId", nullable = false)
    private Book book;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    public long getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        OrderItemId = orderItemId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
