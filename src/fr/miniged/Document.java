package fr.miniged;

import java.time.LocalDateTime;

public class Document {
    private String title;
    private DocumentCategory category;
    private String author;
    private final String uploadedBy;
    private final String originalFileName;
    private final String mimeType;
    private final long fileSize;
    private final String storagePath;
    private final LocalDateTime createdAt;

    public Document(String title, 
            DocumentCategory category, String author, String uploadedBy, String originalFileName,
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DocumentCategory getCategory() {
        return category;
    }

    public void setCategory(DocumentCategory category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Document [title=" + title + ", category=" + category + ", author=" + author + ", uploadedBy="
                + uploadedBy + ", originalFileName=" + originalFileName + ", mimeType=" + mimeType + ", fileSize="
                + fileSize + ", storagePath=" + storagePath + ", createdAt=" + createdAt + "]";
    }
}
