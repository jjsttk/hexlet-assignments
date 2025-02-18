package exercise.controller.users;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;


@Slf4j
@RestController
@RequestMapping("/api")
public class PostsController {
    private final List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable int id) {
        var usersPost = posts.stream().filter(p -> p.getUserId() ==id).toList();
        return ResponseEntity.ok()
                .body(usersPost);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPostByUserId(@PathVariable int id, @RequestBody Post post) {
        if (post.getSlug() == null || post.getTitle() == null || post.getBody() == null) {
            return ResponseEntity.badRequest().build();
        }
        post.setUserId(id);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}

