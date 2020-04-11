package com.hvasoftware.parsehtmlusejsoup;

public class Quote {
    private String content;
    private String author;
    private String category;

    public Quote() {
    }

    public Quote(String content, String author, String category) {
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
