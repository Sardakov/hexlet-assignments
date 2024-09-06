package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var companyID = ctx.pathParam("id");
            Map<String, String> foundCompany = null;

            for (var company : COMPANIES) {
                if (company.get("id").equals(companyID)) {
                    foundCompany = company;
                    break;
                }
            }

            if (foundCompany != null) {
                ctx.json(foundCompany);
            } else {
                ctx.status(404).result("Company not found");
            }
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
