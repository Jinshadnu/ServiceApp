package com.example.serviceapp.home.ui.feedback.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackRepository {
    public NetworkInterface networkInterface;

    public FeedbackRepository() {
    }

    public LiveData<CommonResponse> sendfeedback(String name,String phone,String feedback){
        MutableLiveData mutableLiveData=new MutableLiveData();

        Call<CommonResponse> responseCall=networkInterface.sendfeedback(name,phone,feedback);
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
             mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;

    }
}
