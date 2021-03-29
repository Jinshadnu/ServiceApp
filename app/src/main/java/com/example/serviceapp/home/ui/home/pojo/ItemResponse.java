package com.example.serviceapp.home.ui.home.pojo;

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
}
