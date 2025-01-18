package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.repository.PostRepository;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;


public class PostsController {

    // BEGIN
    public static void navigate(Context ctx) {

        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var postsPerPage = 5;

        if (pageNumber < 1) {
            throw new BadRequestResponse("Invalid page number");
        }
        var filteredPosts = PostRepository.findAll(pageNumber, postsPerPage);

        if (!filteredPosts.isEmpty()) {
            var page = new PostsPage(filteredPosts, pageNumber);
            ctx.render("posts/index.jte", model("page", page));
        } else {
            throw new NotFoundResponse("Post not found");
        }
    }

    public static void viewPost(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id).orElseThrow(NotFoundResponse::new);
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
    // END
}
