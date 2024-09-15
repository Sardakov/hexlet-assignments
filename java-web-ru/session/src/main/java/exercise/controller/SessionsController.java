package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var user = ctx.sessionAttribute("currentUser");
        var page = new MainPage(user);
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage("", "");
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var user = UsersRepository.findByName(ctx.formParam("name"))
                    .orElseThrow(() -> new NotFoundResponse("Wrong username or password"));
            var name = ctx.formParam("name");
            var pass = ctx.formParamAsClass("password", String.class)
                    .check(value -> Security.encrypt(value).equals(user.getPassword()), "Wrong username or password");
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect("/");

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var page = new LoginPage(name, e.getErrors().toString());
            ctx.render("/build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}
