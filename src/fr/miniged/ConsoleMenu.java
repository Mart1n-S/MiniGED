package fr.miniged;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleMenu {
    private static final long SIMULATED_FILE_SIZE = 1024;

    private final DocumentRepository documentRepository;
    private final DocumentClassifier documentClassifier;
    private final Scanner scanner = new Scanner(System.in);
    private String currentUser;

    public ConsoleMenu(DocumentRepository documentRepository, DocumentClassifier documentClassifier) {
        this.documentRepository = documentRepository;
        this.documentClassifier = documentClassifier;
    }

    public void run() {
        currentUser = askNonBlank("Identifiez-vous : ");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1" -> printDocuments(documentRepository.findAll());
                case "2" -> searchByCategory();
                case "3" -> searchByAuthor();
                case "4" -> searchByKeyword();
                case "5" -> addDocument();
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
        System.out.println("5. Ajouter un document");
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

    private void addDocument() {
        System.out.println("=== Ajouter un document ===");
        String title = askNonBlank("Titre du document : ");
        DocumentCategory category = chooseCategory(title);
        String author = askNonBlank("Auteur : ");
        String fileName = askNonBlank("Nom du fichier : ");

        try {
            Document document = new Document(title, category, author, currentUser, fileName,
                    guessMimeType(fileName), SIMULATED_FILE_SIZE, generateStoragePath(fileName));
            documentRepository.add(document);
            System.out.println("Document ajouté : " + document.getTitle() + " (" + document.getCategory() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Impossible d'ajouter le document, vérifiez les informations saisies.");
        }
    }

    private DocumentCategory chooseCategory(String title) {
        DocumentCategory suggestedCategory = documentClassifier.classify(title);
        System.out.println("Catégorie proposée : " + suggestedCategory);
        System.out.println("Appuyez sur Entrée pour l'accepter, ou tapez une autre catégorie : ");
        String categoryName = scanner.nextLine().strip();

        if (categoryName.isBlank()) {
            return suggestedCategory;
        }

        try {
            return DocumentCategory.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Catégorie inconnue, la catégorie proposée est conservée.");
            return suggestedCategory;
        }
    }

    private String guessMimeType(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        if (lowerCaseFileName.endsWith(".pdf")) {
            return "application/pdf";
        } else if (lowerCaseFileName.endsWith(".png")) {
            return "image/png";
        } else {
            return "application/octet-stream";
        }
    }

    private String generateStoragePath(String fileName) {
        LocalDate today = LocalDate.now();
        return String.format("%d/%02d/%s%s",
                today.getYear(), today.getMonthValue(), UUID.randomUUID(), extractExtension(fileName));
    }

    private String extractExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }

        String extension = fileName.substring(dotIndex).toLowerCase();
        if (!extension.matches("\\.[a-z0-9]{1,10}")) {
            return "";
        }
        return extension;
    }
}
