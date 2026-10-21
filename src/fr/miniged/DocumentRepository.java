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
        return this.documents.stream()
                .filter(document -> document.getCategory() == documentCategory)
                .toList();
    }

    public List<Document> findByAuthor(String author) {
        return this.documents.stream()
                .filter(document -> document.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public List<Document> findByTitle(String keyword) {
        return this.documents.stream()
                .filter(document -> document.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
