package com.example.serviceapp.home.ui.home.pojo;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {
  @SerializedName("status")
  public String status;
  @SerializedName("message")
  public String message;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
