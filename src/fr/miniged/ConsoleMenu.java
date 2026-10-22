package fr.miniged;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final DocumentRepository documentRepository;
    private final DocumentClassifier documentClassifier;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(DocumentRepository documentRepository, DocumentClassifier documentClassifier) {
        this.documentRepository = documentRepository;
        this.documentClassifier = documentClassifier;
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1" -> printDocuments(documentRepository.findAll());
                case "2" -> searchByCategory();
                case "3" -> searchByAuthor();
                case "4" -> searchByKeyword();
                case "0" -> {
                    System.out.println("Au revoir !");
                    running = false;
                }
                default -> System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== MiniGED ===");
        System.out.println("1. Afficher tous les documents");
        System.out.println("2. Rechercher par catégorie");
        System.out.println("3. Rechercher par auteur");
        System.out.println("4. Rechercher par mot-clé");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private String askNonBlank(String question) {
        System.out.println(question);
        String answer = scanner.nextLine().strip();
        while (answer.isBlank()) {
            System.out.println("Ce champ est obligatoire, réessayez : ");
            answer = scanner.nextLine().strip();
        }

        return answer;
    }

    private void printDocuments(List<Document> documents) {
        if (documents.isEmpty()) {
            System.out.println("Aucun document trouvé.");
        } else {
            documents.forEach(document -> System.out.println(document.getTitle()));
        }
    }

    private void searchByAuthor() {
        String author = askNonBlank("Nom de l'auteur : ");
        printDocuments(documentRepository.findByAuthor(author));
    }

    private void searchByKeyword() {
        String keyword = askNonBlank("Rechercher par mot-clé : ");
        printDocuments(documentRepository.findByTitle(keyword));
    }

    private void searchByCategory() {
        System.out.println("Voici la liste des catégories : ");
        for (DocumentCategory category : DocumentCategory.values()) {
            System.out.println(category);
        }

        String categoryName = askNonBlank("Veuillez choisir une catégorie : ");
        DocumentCategory category;

        try {
            category = DocumentCategory.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Catégorie inconnue, choisissez-en une dans la liste.");
            return;
        }

        printDocuments(documentRepository.findByCategory(category));
    }
}
