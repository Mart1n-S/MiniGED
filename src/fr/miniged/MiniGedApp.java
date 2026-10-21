package fr.miniged;

import java.util.List;

public class MiniGedApp {
    public static void main(String[] args) {
        System.out.println("MiniGED v0.1 - Gestion électronique de documents");

        Document mobileInvoice = new Document("Facture internet",
                DocumentCategory.INVOICE, "Orange", "Martin", "facture_mobile.pdf",
                "application/pdf", 1024, "2026/10/a3f9ff8k99.pdf");

        Document boxInvoice = new Document("Facture box",
                DocumentCategory.INVOICE, "Orange", "Martin", "facture_box.pdf",
                "application/pdf", 1024, "2026/10/u3bcff33ll.pdf");

        Document contract = new Document("Contrat Toto",
                DocumentCategory.CONTRACT, "TitiEnterprise", "Martin", "contrat_toto.pdf",
                "application/pdf", 2048, "2026/10/99ertg6t7y.pdf");

        Document estimate = new Document("Devis paul",
                DocumentCategory.ESTIMATE, "PaulCORPE", "Martin", "devis_paul.pdf",
                "application/pdf", 2048, "2026/10/67aztg8t0a.pdf");

        DocumentRepository documentRepository = new DocumentRepository();

        documentRepository.add(mobileInvoice);
        documentRepository.add(contract);
        documentRepository.add(estimate);
        documentRepository.add(boxInvoice);

        try {
            Document estimateHouse = new Document("Devis pour la maison",
                    DocumentCategory.ESTIMATE, "PaulCORPE", "Martin", "devis_maison.pdf",
                    "application/pdf", -10, "2026/10/07niog110a.pdf");
            documentRepository.add(estimateHouse);
        } catch (IllegalArgumentException e) {
            System.out.println("Impossible de créer le document : " + e.getMessage());
        }

        printTitles("Tous les documents : ", documentRepository.findAll());
        printTitles("Toutes les factures : ", documentRepository.findByCategory(DocumentCategory.INVOICE));
        printTitles("Tous les documents par auteur orange : ", documentRepository.findByAuthor("orange"));
        printTitles("Tous les documents par auteur PaulCORPE : ", documentRepository.findByAuthor("PaulCORPE"));
        printTitles("Tous les documents trouvés par mot clé 'facture' : ", documentRepository.findByTitle("facture"));
        printTitles("Tous les documents trouvés par mot clé 'TOTO' : ", documentRepository.findByTitle("TOTO"));
    }

    private static void printTitles(String heading, List<Document> documents) {
        System.out.println(heading);
        documents.forEach(document -> System.out.println(document.getTitle()));
    }
}