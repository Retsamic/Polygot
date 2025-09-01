package org.example;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        SnippetDAO snippetDAO = new SnippetDAO(dbManager);

        // 2. Create a new Snippet object to save
        Snippet newSnippet = new Snippet();
        newSnippet.setTitle("First JDBC Insert");
        newSnippet.setLanguage("Java");
        newSnippet.setCode_body("System.out.println(\"Hello, JDBC!\");");
        newSnippet.setNotes("This is my first time saving to the database with Java.");

        newSnippet.setId(3);
        snippetDAO.delete(newSnippet);
    }
}