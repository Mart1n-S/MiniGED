package fr.miniged;

public class MiniGedApp {
    public static void main(String[] args) {
        System.out.println("MiniGED v0.1 - Gestion électronique de documents");

        Document factureMobile = new Document("Facture internet", 
                DocumentCategory.INVOICE, "Orange", "Martin", "facture_mobile.pdf",
                "application/pdf", 1024, "2026/10/a3f9ff8k99.pdf");

        Document contrat = new Document("Contrat Toto", 
                DocumentCategory.CONTRACT, "TitiEnterprise", "Martin", "contrat_toto.pdf",
                "application/pdf", 2048, "2026/10/99ertg6t7y.pdf");

        System.out.println(factureMobile);

        System.out.println(contrat);

        factureMobile.setTitle("Facture téléphonie mobile");

        System.out.println(factureMobile);

    }
}