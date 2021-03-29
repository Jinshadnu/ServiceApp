package com.example.serviceapp.home.ui.home.pojo;



import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponse {
    @SerializedName("news")
    public ArrayList<Newses> news;

    @SerializedName("status")
    public String status;

    public ArrayList<Newses> getNews() {
        return news;
    }

    public String getStatus() {
        return status;
    }

    public class Newses {


        @SerializedName("image")
        public String image;

        @SerializedName("data")
        public String data;

        @SerializedName("heading")
        public String heading;

        @SerializedName("id")
        public String id;

        public String getImage() {
            return image;
        }

        public String getData() {
            return data;
        }

        public String getHeading() {
            return heading;
        }

        public String getId() {
            return id;
        }
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}

