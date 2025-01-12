package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
                    var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
                    var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
                    int startIndex = ((page * per) - 1) < 0 ? 0 : page * per - per;
                    var endIndex = Math.min(startIndex + per, USERS.size() - 1);
                    ctx.json(USERS.subList(startIndex, endIndex));
                });
            // END
            return app;
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
