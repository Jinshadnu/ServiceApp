package com.example.serviceapp.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SubCategory {
    public int sub_category_id;
    public String sub_category_name;
    public int sub_category_image;

    public SubCategory(String sub_category_name, int sub_category_image) {
        this.sub_category_name = sub_category_name;
        this.sub_category_image = sub_category_image;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public int getSub_category_image() {
        return sub_category_image;
    }

    @BindingAdapter("subcategory")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }
}
