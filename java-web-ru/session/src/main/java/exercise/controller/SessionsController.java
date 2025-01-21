package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    // BEGIN
    public static void getLoginPage(Context ctx) {
        var page = new LoginPage("", null);
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        var user = UsersRepository.getEntities()
                .stream()
                .filter(u -> u.getName().equalsIgnoreCase(name) && u.getPassword().equals(Security.encrypt(password)))
                .findFirst();
        if (user.isPresent()) {
            ctx.sessionAttribute("user", user.get());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            ctx.sessionAttribute("user", null);
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("user", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void getRootPage(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("user"));
        ctx.render("index.jte", model("page", page));
    }
    // END
}
