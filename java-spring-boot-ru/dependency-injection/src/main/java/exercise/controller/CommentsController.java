package exercise.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentsController {
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> showAll() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Comment show(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id = " + id + " Not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping(path = "{id}")
    public Comment update(@RequestBody Comment comment,
                          @PathVariable Long id) {
        var maybeComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id = " + id + " Not found"));
        maybeComment.setBody(comment.getBody());
        return commentRepository.save(maybeComment);
    }

    @DeleteMapping(path = "{id}")
    public void destroy(@PathVariable Long id) {
        var maybeComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id = " + id + " Not found"));
        commentRepository.delete(maybeComment);
    }

}
// END
