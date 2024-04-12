package hkmu.comps380f.controller;

import hkmu.comps380f.dao.BookService;
import hkmu.comps380f.dao.CommentService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;
    @Resource
    private CommentService commentService;

    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("bookDatabase", bookService.getBooks());
        return "list";
    }

    public static class BookForm {
        private String title;
        private String author;
        private long price;
        private String description;
        private int stock;
        private MultipartFile image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public MultipartFile getImage() {
            return image;
        }

        public void setImage(MultipartFile image) {
            this.image = image;
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "bookForm", new BookForm());
    }
    @PostMapping("/create")
    public View create(BookForm form) throws IOException {
        long bookId = bookService.addBook(form.getTitle(), form.getAuthor(), form.getDescription(), form.getPrice(), form.getStock(), form.getImage());
        return new RedirectView("/book/view/" + bookId, true);
    }

    @Autowired
    public BookController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @GetMapping("/view/{bookId}")
    public String view(@PathVariable("bookId") long bookId, ModelMap model) throws BookNotFound {
        Book book = bookService.getBook(bookId);
        List<Comment> comments = commentService.getComments(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        return "view";
    }

    @GetMapping("/image/{bookId}")
    public View download(@PathVariable("bookId") long bookId) throws BookNotFound {
        Book book = bookService.getBook(bookId);
        return new DownloadingView(book.getFilename(), book.getMimeContentType(), book.getContents());
    }

    @GetMapping("/edit/{bookId}") // not sure if editing cover image should be added
    public ModelAndView showEdit(@PathVariable("bookId") long bookId, HttpServletRequest request) throws BookNotFound {
        Book book = bookService.getBook(bookId);
        if (book == null || !request.isUserInRole("ROLE_ADMIN")) {
            return new ModelAndView(new RedirectView("/book/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", book);
        BookForm bookForm = new BookForm();
        bookForm.setTitle(book.getTitle());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setDescription(book.getDescription());
        bookForm.setPrice(book.getPrice());
        bookForm.setStock(book.getStock());
        return modelAndView;
    }
    @PostMapping("/edit/{bookId}")
    public String edit(@PathVariable("bookId") long bookId, BookForm form, HttpServletRequest request) throws IOException, BookNotFound {
        Book book = bookService.getBook(bookId);
        if (book == null || !request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/book/list";
        }
        bookService.updateBook(bookId, form.getTitle(), form.getAuthor(), form.getDescription(), form.getPrice(), form.getStock());
        return "redirect:/book/view/" + bookId;
    }

    @GetMapping("/delete/{bookId}")
    public String removeBook(@PathVariable("bookId") long bookId) throws BookNotFound {
        bookService.delete(bookId);
        return "redirect:/book/list";
    }

    @ExceptionHandler({BookNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
