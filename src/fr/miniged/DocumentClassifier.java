package fr.miniged;

import java.util.Map;

public class DocumentClassifier {
    private static final Map<String, DocumentCategory> RULES = Map.of(
            "facture", DocumentCategory.INVOICE,
            "contrat", DocumentCategory.CONTRACT,
            "bulletin de paie", DocumentCategory.PAY_SLIP,
            "attestation", DocumentCategory.CERTIFICATE,
            "courrier", DocumentCategory.MAIL,
            "compte rendu", DocumentCategory.MEETING_MINUTES,
            "devis", DocumentCategory.ESTIMATE);

    public DocumentCategory classify(String title) {
        if (title == null || title.isBlank()) {
            return DocumentCategory.OTHER;
        }

        String normalizedTitle = title.toLowerCase();

        for (Map.Entry<String, DocumentCategory> rule : RULES.entrySet()) {
            if (normalizedTitle.contains(rule.getKey())) {
                return rule.getValue();
            }
        }

        return DocumentCategory.OTHER;
    }
}
