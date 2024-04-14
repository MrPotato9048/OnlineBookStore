package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Book;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    @Resource
    private BookRepository bookRepo;

    @Transactional
    public List<Book> getBooks() { return bookRepo.findAll(); }

    @Transactional
    public Book getBook(long id) throws BookNotFound {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFound(id);
        }
        return book;
    }

    public long addBook(String title, String author, String description, long price, int stock, MultipartFile image) throws IOException {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        book.setStock(stock);
        book.setFilename(image.getOriginalFilename());
        book.setMimeContentType(image.getContentType());
        book.setContents(image.getBytes());
        Book savedBook = bookRepo.save(book);
        return savedBook.getId();
    }

    @Transactional(rollbackOn = BookNotFound.class)
    public void delete(long id) throws BookNotFound {
        Book removedBook = bookRepo.findById(id).orElse((null));
        if (removedBook == null) {
            throw new BookNotFound(id);
        }
        bookRepo.delete(removedBook);
    }

    @Transactional
    public void updateBook(long id, String title, String author, String description, long price, int stock) throws IOException, BookNotFound {
        Book updatedBook = bookRepo.findById(id).orElse(null);
        if (updatedBook == null) {
            throw new BookNotFound(id);
        }
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook.setDescription(description);
        updatedBook.setPrice(price);
        updatedBook.setStock(stock);
        bookRepo.save(updatedBook);
    }
}
