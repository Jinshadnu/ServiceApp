package com.example.serviceapp.home.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.serviceapp.R;
import com.example.serviceapp.core.NetworKService;
import com.example.serviceapp.core.NetworkInterface;
import com.example.serviceapp.home.ui.home.pojo.CategoryResponse;
import com.example.serviceapp.home.ui.home.pojo.Services;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRepository {
    public NetworkInterface networkInterface;


    public ServiceRepository() {
    }

//    public LiveData<List<Services>> getServices(){
//        MutableLiveData mutableLiveData=new MutableLiveData();
//
//        List<Services> servicesList=new ArrayList<>();
//        servicesList.add(new Services("News", R.drawable.ic_newspaper__1_));
//        servicesList.add(new Services("Autoriksha", R.drawable.ic_rickshaw__1_));
//        servicesList.add(new Services("Vehicles", R.drawable.ic_car));
//        servicesList.add(new Services("Hospitals", R.drawable.ic_hospital__1_));
//        servicesList.add(new Services("Workers", R.drawable.ic_workers));
//        servicesList.add(new Services("Shops", R.drawable.ic_shop));
//        servicesList.add(new Services("Blood Bank", R.drawable.ic_blood_bank));
//        servicesList.add(new Services("Bus Timings", R.drawable.ic_bus));
//        servicesList.add(new Services("Classifieds", R.drawable.ic_classified));
//        servicesList.add(new Services("Blood Bank", R.drawable.ic_blood_bank));
//        servicesList.add(new Services("Directory", R.drawable.ic_landline));
//        servicesList.add(new Services("Buildings", R.drawable.ic_office_building));
//        servicesList.add(new Services("Education", R.drawable.ic_school));
//
//
//        mutableLiveData.setValue(servicesList);
//
//        return mutableLiveData;
//    }

    public LiveData<CategoryResponse> getCategories(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= NetworKService.getRetrofitInstance().create(NetworkInterface.class);
        Call<CategoryResponse> responseCall=networkInterface.getcategories();

        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse categoryResponse=response.body();
                if (categoryResponse != null){
                    mutableLiveData.setValue(categoryResponse);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
      return mutableLiveData;
    }
}
