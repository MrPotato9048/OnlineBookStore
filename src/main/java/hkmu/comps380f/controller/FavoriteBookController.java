package hkmu.comps380f.controller;

import hkmu.comps380f.dao.FavoriteBookService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.FavoriteBookNotFound;
import hkmu.comps380f.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorite")
public class FavoriteBookController {

    @Autowired
    private FavoriteBookService favoriteBookService;

    @PostMapping("/add/{bookId}")
    public String addFavoriteBook(@PathVariable("bookId") long bookId, Principal principal) throws BookNotFound {
        favoriteBookService.addFavoriteBook(bookId, principal.getName());
        return "redirect:/book/view/" + bookId;
    }

    @PostMapping("/remove/{bookId}")
    public String removeFavoriteBook(@PathVariable("bookId") long bookId, Principal principal) throws FavoriteBookNotFound {
        favoriteBookService.removeFavoriteBook(bookId, principal.getName());
        return "redirect:/book/view/" + bookId;
    }
}
