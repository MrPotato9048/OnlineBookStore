package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CommentService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable long bookId) {
        List<Comment> comments = commentService.getComments(bookId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/add/{bookId}")
    public String addComment(@PathVariable("bookId") long bookId, @RequestParam("commentText") String commentText, Principal principal) throws BookNotFound {
        commentService.saveComment(bookId, commentText, principal.getName());
        return "redirect:/book/view/" + bookId;
    }

    @GetMapping("/delete/{bookId}/{id}")
    public String deleteComment(@PathVariable("bookId") long bookId, @PathVariable("id") long id) throws CommentNotFound {
        commentService.deleteComment(id);
        return "redirect:/book/view/" + bookId;
    }
}