package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryRepository {
    public NetworkInterface networkInterface;

    public SubCategoryRepository() {

    }

    public LiveData<SubCategoryResponse> getSubCategories(String category_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<SubCategoryResponse> responseCall=networkInterface.getSubCategories(category_id);
        responseCall.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                SubCategoryResponse subCategoryResponse=response.body();
                if (subCategoryResponse != null){
                   mutableLiveData.setValue(subCategoryResponse);
                }

            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return  mutableLiveData;
    }
}
