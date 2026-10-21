package fr.miniged;

import java.util.List;
import java.util.ArrayList;

public class MiniGedApp {
    public static void main(String[] args) {
        System.out.println("MiniGED v0.1 - Gestion électronique de documents");

        Document mobileInvoice = new Document("Facture internet",
                DocumentCategory.INVOICE, "Orange", "Martin", "facture_mobile.pdf",
                "application/pdf", 1024, "2026/10/a3f9ff8k99.pdf");

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

        documentRepository.findAll().forEach(document -> System.out.println(document.getTitle()));
    }
}