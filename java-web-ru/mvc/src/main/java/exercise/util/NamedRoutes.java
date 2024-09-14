package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN
    public static String postEdit(String id) {
        return "/posts/" + id  + "/edit";
    }

    public static String postEdit(Long id) {
        return postEdit(String.valueOf(id));
    }
    // END
}
