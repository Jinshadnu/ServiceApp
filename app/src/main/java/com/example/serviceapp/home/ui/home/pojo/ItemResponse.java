package com.example.serviceapp.home.ui.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemResponse {
    @SerializedName("data")
    public ArrayList<Datas> data;

    @SerializedName("status")
    public String status;

    public ArrayList<Datas> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public class Datas {
        @SerializedName("phone")
        public String phone;

        @SerializedName("name")
        public String name;

        @SerializedName("place")
        public String place;
        
        @SerializedName("image")
        public String image;

        public String getImage() {
            return image;
        }

        public String getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }

        public String getPlace() {
            return place;
        }
    }

    @BindingAdapter({"item_pic"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
