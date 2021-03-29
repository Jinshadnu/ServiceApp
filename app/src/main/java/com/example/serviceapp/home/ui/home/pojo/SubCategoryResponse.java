package com.example.serviceapp.home.ui.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SubCategoryResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("sub_categories")
    public ArrayList<Sub_categories> sub_categories;

    public String getStatus() {
        return status;
    }

    public ArrayList<Sub_categories> getSub_categories() {
        return sub_categories;
    }

    public class Sub_categories {
        @SerializedName("sub_category_id")
        public String sub_category_id;

        @SerializedName("sub_category_name")
        public String sub_category_name;

        @SerializedName("sub_category_pic")
        public String sub_category_pic;

        public String getSub_category_id() {
            return sub_category_id;
        }

        public String getSub_category_name() {
            return sub_category_name;
        }

        public String getSub_category_pic() {
            return sub_category_pic;
        }
    }

    @BindingAdapter({"sub_category_pic"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions())
                .into(view);
    }
}
