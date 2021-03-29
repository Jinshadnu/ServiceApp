package com.example.serviceapp.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class News {
    public int news_id;
    public String news_subject;
    public int news_image;

    public News(String news_subject, int news_image) {
        this.news_subject = news_subject;
        this.news_image = news_image;
    }

    public int getNews_id() {
        return news_id;
    }

    public String getNews_subject() {
        return news_subject;
    }

    public int getNews_image() {
        return news_image;
    }

    @BindingAdapter("news")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }
}
