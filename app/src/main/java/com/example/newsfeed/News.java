package com.example.newsfeed;


public class News {
    public News(String author, String title, String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    String author;
    String title;
    String url;
    String urlToImage;
}
