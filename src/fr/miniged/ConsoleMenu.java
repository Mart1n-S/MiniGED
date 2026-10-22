package fr.miniged;

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
                case "1" -> documentRepository.findAll()
                        .forEach(document -> System.out.println(document.getTitle()));
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
}
