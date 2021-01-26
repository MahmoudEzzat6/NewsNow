package com.NewsNow.newsnowMa.ApiService;

import java.util.ArrayList;

public class NewsModel {
    public ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
class Article{
    private String author;
    private String title;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    private String url;
    private String urlToImage;

}