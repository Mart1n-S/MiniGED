package fr.miniged;

import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    private final List<Document> documents = new ArrayList<>();

    public void add(Document document) {
        this.documents.add(document);
    }

    public List<Document> findAll() {
        return List.copyOf(this.documents);
    }

    public List<Document> findByCategory(DocumentCategory documentCategory) {
        List<Document> results = new ArrayList<>();

        for (Document document : this.documents) {
            if (document.getCategory() == documentCategory) {
                results.add(document);
            }
        }

        return results;
    }

    public List<Document> findByAuthor(String author) {
        List<Document> results = new ArrayList<>();

        for (Document document : this.documents) {
            if (document.getAuthor().equalsIgnoreCase(author)) {
                results.add(document);
            }
        }

        return results;
    }
}
