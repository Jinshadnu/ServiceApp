package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddvertisementRepository {
    public NetworkInterface networkInterface;


    public AddvertisementRepository() {
    }

    public LiveData<CommonResponse> addAdvertsement(String name,String phone,String message){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);

        Call<CommonResponse> responseCall=networkInterface.addAdvertisement(name, phone, message);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null){
                    mutableLiveData.postValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
