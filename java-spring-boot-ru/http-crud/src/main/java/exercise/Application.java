package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        int from = (page - 1) * limit;
        int to = from + limit;
        return posts.subList(from, to);
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    public Post post(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post post) {
        var temp = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (temp.isPresent()) {
            temp.get().setTitle(post.getTitle());
            temp.get().setBody(post.getBody());
        }
        return post;
    }
    // END
}
