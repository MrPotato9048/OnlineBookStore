package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.FavoriteBookNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Favorite;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteBookService {
    @Resource
    private FavoriteRepository favoriteRepo;
    @Resource
    private BookRepository bookRepo;
    @Resource
    private AppUserRepository appUserRepo;

    @Transactional
    public List<Favorite> getFavoriteBooks() { return favoriteRepo.findAll(); }

    @Transactional
    public void addFavoriteBook(long bookId, String username) throws BookNotFound, UsernameNotFoundException {
        System.out.println("addFavoriteBook is called.");
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            System.out.println("Book not found.");
            throw new BookNotFound(bookId);
        }
        AppUser user = appUserRepo.findById(username).orElse(null);
        if (user == null) {
            System.out.println("User not found.");
            throw new UsernameNotFoundException(username);
        }
        Favorite favoriteBook = new Favorite();
        favoriteBook.setAppUser(user);
        favoriteBook.setBook(book);
        favoriteRepo.save(favoriteBook);
        System.out.println("Favorite book added.");
    }

    @Transactional
    public void removeFavoriteBook(long bookId, String username) throws FavoriteBookNotFound {
        AppUser user = appUserRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Favorite favorite = favoriteRepo.findByAppUserAndBookId(user, bookId);
        if (favorite == null) {
            throw new FavoriteBookNotFound(bookId);
        }
        favoriteRepo.delete(favorite);
    }

    @Transactional
    public boolean isFavoriteBook(long bookId, String username) {
        AppUser user = appUserRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Favorite favorite = favoriteRepo.findByAppUserAndBookId(user, bookId);
        return favorite != null;
    }

    public List<Favorite> getFavoriteBooksByUsername(String username){
        AppUser user = appUserRepo.findByUsername(username);
        return favoriteRepo.findByAppUser(user);
    }
}

