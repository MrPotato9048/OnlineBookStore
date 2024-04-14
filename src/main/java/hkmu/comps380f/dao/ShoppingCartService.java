package hkmu.comps380f.dao;

import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.ShoppingCart;
import hkmu.comps380f.model.ShoppingCartItem;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.AppUser;

import org.eclipse.tags.shaded.java_cup.runtime.lr_parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepo;

    @Autowired
    private AppUserRepository appUserRepo;

    @Autowired
    private BookRepository bookRepo;

    public ShoppingCart getShoppingCart(String username) {
        AppUser appUser = appUserRepo.findById(username).orElse(null);
        if (appUser == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        ShoppingCart shoppingCart = shoppingCartRepo.findByAppUser(appUser);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setAppUser(appUser);
            shoppingCartRepo.save(shoppingCart);
        }
        return shoppingCart;
    }

    public void addShoppingCartItem(String username, long bookId, int quantity) {
        ShoppingCart shoppingCart = getShoppingCart(username);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        Book book = optionalBook.get();
        shoppingCartItem.setBook(book);
        shoppingCartItem.setQuantity(quantity);
        shoppingCart.addShoppingCartItem(shoppingCartItem);
        shoppingCartRepo.save(shoppingCart);
    }

    public void removeShoppingCartItem(String username, long bookId) {
        ShoppingCart shoppingCart = getShoppingCart(username);
        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        for (int i = 0; i < shoppingCartItems.size(); i++) {
            ShoppingCartItem shoppingCartItem = shoppingCartItems.get(i);
            if (shoppingCartItem.getBook().getId() == bookId) {
                shoppingCart.removeShoppingCartItem(shoppingCartItem);
                shoppingCartRepo.save(shoppingCart);
                break;
            }
        }
    }

    public List<ShoppingCartItem> getShoppingCartItems(String username) {
        ShoppingCart shoppingCart = getShoppingCart(username);
        return shoppingCart.getShoppingCartItems();
    }

    public void clearShoppingCart(String username) {
        ShoppingCart shoppingCart = getShoppingCart(username);
        shoppingCart.getShoppingCartItems().clear();
        shoppingCartRepo.save(shoppingCart);
    }
}
