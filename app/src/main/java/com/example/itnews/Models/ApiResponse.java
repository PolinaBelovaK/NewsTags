package com.example.itnews.Models;

import java.io.Serializable;
import java.util.List;

public class ApiResponse implements Serializable {
    String status;
    int totalResult;
    List<Headlines> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Headlines> getArticles() {
        return articles;
    }

    public void setArticles(List<Headlines> articles) {
        this.articles = articles;
    }
}
