package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    public NetworkInterface networkInterface;


    public NewsRepository() {
    }


    public LiveData<NewsResponse> getNews(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<NewsResponse> responseCall=networkInterface.getNews();

        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse=response.body();
                if (newsResponse != null){
                    mutableLiveData.setValue(newsResponse);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
            mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
