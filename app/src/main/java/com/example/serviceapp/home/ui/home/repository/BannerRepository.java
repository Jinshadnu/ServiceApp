package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.BannerResponse;
import com.example.serviceapp.util.NetworkUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerRepository {

    public BannerRepository() {

    }

    public NetworkInterface networkInterface;

    public LiveData<BannerResponse> getBanners(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<BannerResponse> responseCall=networkInterface.getBanners();
        responseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                BannerResponse bannerResponse=response.body();
                if (bannerResponse !=null){
                    mutableLiveData.setValue(bannerResponse);
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }


}
