package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return rootPath() + "posts";
    }

    public static String postPath(String id) {
        return rootPath() + "posts/" + id;
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String nextPage(int page) {
        return postsPath() + "?page=" + (page + 1);
    }
    public static String prevPage(int page) {
        return postsPath() + "?page=" + (page - 1);
    }

    // END
}
