package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();
    private static final SnippetDAO snippetDAO = new SnippetDAO(dbManager);
    private static final TagsDAO tagsDAO = new TagsDAO(dbManager);
    private static final SnippetTagDAO snippetTagDAO = new SnippetTagDAO(dbManager);
    private static final Scanner scanner = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n--- Welcome to Polyglot! ---");
        System.out.println("1. Add a new snippet");
        System.out.println("2. View all snippets");
        System.out.println("3. Find a snippet by ID");
        System.out.println("4. Update Snippet");
        System.out.println("5. Delete Snippet");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    private static void addNewSnippet() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter language: ");
        String language = scanner.nextLine();
        System.out.print("Enter code body: ");
        String codeBody = scanner.nextLine();
        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();

        Snippet newSnippet = new Snippet();
        newSnippet.setTitle(title);
        newSnippet.setLanguage(language);
        newSnippet.setCode_body(codeBody);
        newSnippet.setNotes(notes);
        // The database will set the ID and created_at timestamp automatically

        snippetDAO.createNote(newSnippet);
        System.out.println("Snippet saved successfully!");
    }

    private static void viewAllSnippets() {
        List<Snippet> snippets = snippetDAO.findAll();
        if (snippets.isEmpty()){
            System.out.println("No snippets found.");
            return;
        }
        for(Snippet snippet : snippets){
            System.out.println(snippet.toString()); // Uses the toString() method in Snippet.java
        }
    }

    private static void findSnippetById() {
        System.out.print("Enter snippet ID to find: ");
        int id = Integer.parseInt(scanner.nextLine());
        Snippet snippet = snippetDAO.findById(id);

        if (snippet != null) {
            System.out.println("ID: " + snippet.getId());
            System.out.println("Title: " + snippet.getTitle());
            System.out.println("Language: " + snippet.getLanguage());
            System.out.println("Code: \n" + snippet.getCode_body());
            System.out.println("Notes: \n" + snippet.getNotes());
            System.out.println("Created At: " + snippet.getCreated_at());
        } else {
            System.out.println("Snippet with ID " + id + " not found.");
        }
    }

    private static void updateSnippet(){
        System.out.print("Enter snippet ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Snippet snippet = snippetDAO.findById(id);
        if(snippet == null){
            System.out.println("No snippet with that id");
            return;
        }
        System.out.println("Enter new title. Press enter to leave it as is. Current: " + snippet.getTitle() );
        String title = scanner.nextLine();
        if(!title.isEmpty()){
            snippet.setTitle(title);
        }
        System.out.println("Enter new code body. Press enter to leave it as is. Current: " + snippet.getCode_body());
        String codeBody = scanner.nextLine();
        if(!codeBody.isEmpty()){
            snippet.setCode_body(codeBody);
        }
        System.out.println("Enter new notes. Press enter to leave it as is. Current: " + snippet.getNotes());
        String notes = scanner.nextLine();
        if(!notes.isEmpty()){
            snippet.setNotes(notes);
        }
        snippetDAO.updateSnippet(snippet);
        System.out.println("Snippet updated successfully!");
    }

    public static void deleteSnippet(){
        System.out.println("Enter the id of the snippet you would like to delete");
        int id = Integer.parseInt(scanner.nextLine());
        Snippet snippet = snippetDAO.findById(id);
        if(snippet == null){
            System.out.println("This snippet id does not exist.");
        }
        snippetDAO.delete(id);
        System.out.println("Snippet with ID " + id + " deleted.");
    }

    private static void manageTagsForSnippet() {
        System.out.print("Enter the ID of the snippet to manage tags for: ");
        int snippetId = Integer.parseInt(scanner.nextLine());
        Snippet snippet = snippetDAO.findById(snippetId);

        if (snippet == null) {
            System.out.println("Snippet not found.");
            return;
        }

        System.out.println("\nCurrent tags for snippet '" + snippet.getTitle() + "':");
        List<Tags> currentTags = snippetTagDAO.findTagsForSnippets(snippetId);
        if (currentTags.isEmpty()) {
            System.out.println(" (None)");
        } else {
            for (Tags tag : currentTags) {
                System.out.println(" - " + tag.getName());
            }
        }

        System.out.println("\n1. Add a tag");
        System.out.println("2. Return to main menu");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Enter tag name to add (e.g., 'java', 'sql'): ");
            String tagName = scanner.nextLine();

            Tags tag = tagsDAO.findByName(tagName);
            if (tag == null) {
                tag = new Tags();
                tag.setName(tagName);
                tagsDAO.createTag(tag);

                tag = tagsDAO.findByName(tagName);
                System.out.println("Created new tag: '" + tagName + "'");
            }
            snippetTagDAO.addTagToSnippet(snippetId, tag.getId());
            System.out.println("Successfully tagged snippet with '" + tagName + "'!");
        }
    }

    private static void findSnippetsByTag(){
        System.out.println("Enter tag name to search for (e.g., 'java'):");
        String tagName = scanner.nextLine().trim().toLowerCase();
        Tags tag = tagsDAO.findByName(tagName);

        if(tag == null){
            System.out.println("Tag '" + tagName + "' does not exist.");
        }
        List<Snippet> snippets = snippetTagDAO.findSnippetsForTags(tag.getId());
        if(snippets.isEmpty()){
            System.out.println("No snippets found with the tag '" + tagName + "'.");
        }

        System.out.println("\nSnippets tagged with '" + tagName + "':");
        for (Snippet snippet : snippets){
            System.out.println(snippet.toString());
        }

    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewSnippet();
                    break;
                case 2:
                    viewAllSnippets();
                    break;
                case 3:
                    findSnippetById();
                    break;
                case 4:
                    updateSnippet();
                    break;
                case 5:
                    deleteSnippet();
                    break;
                case 6:
                    manageTagsForSnippet();
                    break;
                case 7:
                    findSnippetsByTag();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }
}