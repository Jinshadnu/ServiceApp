package com.example.serviceapp.home.ui.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("categories")
    public ArrayList<Categories> categories;

    public String getStatus() {
        return status;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public class Categories {
        @SerializedName("category_name")
        public String category_name;

        @SerializedName("category_id")
        public String category_id;

        @SerializedName("category_pic")
        public String category_pic;

        public String getCategory_name() {
            return category_name;
        }

        public String getCategory_id() {
            return category_id;
        }

        public String getCategory_pic() {
            return category_pic;
        }
    }

    @BindingAdapter({"category_pic"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
