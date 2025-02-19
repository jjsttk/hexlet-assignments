package exercise.controller;

import exercise.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostsController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> showAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Post show(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        var maybePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        maybePost.setBody(post.getBody());
        maybePost.setTitle(post.getTitle());
        return postRepository.save(maybePost);
    }

    @DeleteMapping(path = "{id}")
    public void destroy(@PathVariable Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var isHaveComments = commentRepository.findAll()
                .stream()
                .anyMatch(comment -> comment.getPostId() == post.getId());
        if (isHaveComments) {
            commentRepository.deleteByPostId(id);
        }
        postRepository.delete(post);
    }
}
// END
