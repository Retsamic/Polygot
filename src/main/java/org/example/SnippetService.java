package org.example;

import java.sql.SQLException;

public class SnippetService {
    private final SnippetDAOJdbc snippetDAOJdbc;

    public SnippetService(){
        this.snippetDAOJdbc = new SnippetDAOJdbc(null);
    }

    public Snippet createSnippet(Snippet snippet)throws SQLException {
        if(snippet.getNotes().isEmpty() || snippet.getCode_body().isEmpty()){
            return null;
        }
        return snippet;
    }
}
