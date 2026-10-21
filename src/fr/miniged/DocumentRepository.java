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
}
