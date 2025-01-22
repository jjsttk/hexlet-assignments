package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class).check((str -> str.length() >= 2), "Name must be longer or equals 2").get();
            var body = ctx.formParamAsClass("body", String.class).get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.sessionAttribute("flash", "Post was successfully created!");
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParamAsClass("name", String.class).get();
            var body = ctx.formParamAsClass("body", String.class).get();
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page));
        }
    }

    //show posts
    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        String flash = ctx.consumeSessionAttribute("flash");
        var page = new PostsPage(posts);
        page.setFlash(flash);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
