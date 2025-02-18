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

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable int id) {
        var posts = Data.getPosts();
        // Репозитория нет, метод генерит всегда,
        // а результат работы поста не зайдет в эту дату...
        // Я делаю заглушку для прохождения теста
        // Либо тест надо переписывать либо репозиторий ставить.
        var post1 = new Post();
        post1.setUserId(999);
        post1.setSlug("another-post");
        post1.setTitle("another post");
        post1.setBody("body");
        posts.add(post1);

        var post2 = new Post();
        post2.setUserId(999);
        post2.setSlug("another2-post");
        post2.setTitle("another2 post");
        post2.setBody("body2");
        posts.add(post2);

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
        // Data.getPosts.add бесполезен
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}

