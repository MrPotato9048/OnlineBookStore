package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Comment;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Resource
    private CommentRepository commentRepo;
    @Resource
    private BookRepository bookRepo;
    @Resource
    private AppUserRepository appUserRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Comment> getComments(long bookId) {
        List<Comment> comments = commentRepo.findByBookId(bookId);
        return comments != null ? comments : new ArrayList<>();
    }

    @Transactional
    public Comment getComment(long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        return comment;
    }

    @Transactional
    public Comment saveComment(long bookId, String commentText, String username) throws BookNotFound, UsernameNotFoundException {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            throw new BookNotFound(bookId);
        }
        AppUser user = appUserRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setBook(book);
        comment.setAppUser(user);
        return commentRepo.save(comment);
    }

    @Transactional(rollbackOn = CommentNotFound.class)
    public void deleteComment(long id) throws CommentNotFound {
        Comment deletedComment = commentRepo.findById(id).orElse(null);
        if (deletedComment == null) {
            throw new CommentNotFound(id);
        }
        commentRepo.delete(deletedComment);
        entityManager.flush();
    }
}
