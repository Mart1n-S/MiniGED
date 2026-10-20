package fr.miniged;

import java.time.LocalDateTime;

public class Document {
    private String title;
    private String category;
    private String author;
    private String uploadedBy;
    private String originalFileName;
    private String mimeType;
    private long fileSize;
    private String storagePath;
    private LocalDateTime createdAt;

    public Document(String title, String category, String author, String uploadedBy, String originalFileName,
            String mimeType, long fileSize, String storagePath) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.uploadedBy = uploadedBy;
        this.originalFileName = originalFileName;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.storagePath = storagePath;
        this.createdAt = LocalDateTime.now();
    }
}
