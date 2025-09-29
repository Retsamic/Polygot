package org.example;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnippetController {
    SnippetService snippetService = new SnippetService();
    SnippetDAOJdbc snippetDAOJdbc = new SnippetDAOJdbc(null);

    public Javalin startAPI(){
    Javalin app = Javalin.create();
    app.get("/snippet", this::getAllSnippets);
    app.get("/snippet/{snippet_id}", this::getSnippet);
    app.post("/snippet", this::createSnippet);
    app.delete("/snippet/{snippet_id}", this::getSnippet);
    app.patch("/snippet/{snippet_id}", this::updateSnippet);


    return app;
    }

    private void getAllSnippets(Context ctx) throws SQLException {
        List <Snippet> snippet =
    }

    private void getSnippet(Context ctx) {

    }

    private void createSnippet(Context ctx) {

    }

    private void deleteSnippet(Context ctx) {

    }

    private void updateSnippet(Context ctx) {

    }
}
