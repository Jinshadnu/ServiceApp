package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.CommonResponse;
import com.example.serviceapp.home.ui.home.pojo.ItemResponse;
import com.example.serviceapp.util.NetworkUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    public NetworkInterface  networkInterface;


    public ItemRepository() {
    }

    public LiveData<ItemResponse> getItems(String subCategory_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<ItemResponse> responseCall=networkInterface.getItems(subCategory_id);
        responseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse itemResponse=response.body();
                if (itemResponse != null){
                    mutableLiveData.setValue(itemResponse);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
            mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<CommonResponse> addItems(String name,String phone,String place,String subcategory_id,String image,String pincode){
        MutableLiveData  mutableLiveData=new MutableLiveData();

        networkInterface=NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall = networkInterface.addItems(name,phone,place,subcategory_id,image,pincode);
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

   return  mutableLiveData;
    }
}
