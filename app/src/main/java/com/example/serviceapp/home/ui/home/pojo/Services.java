package com.example.serviceapp.home.ui.home.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Services {
    public int service_id;
    public String service_name;

    public int getService_id() {
        return service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public int getService_image() {
        return service_image;
    }

    public int service_image;

    public Services(String service_name, int service_image) {
        this.service_name = service_name;
        this.service_image = service_image;
    }

    @BindingAdapter("services")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }





}
