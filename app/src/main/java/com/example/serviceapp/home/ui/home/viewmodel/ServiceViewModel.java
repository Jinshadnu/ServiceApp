package com.example.serviceapp.home.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.home.pojo.CategoryResponse;
import com.example.serviceapp.home.ui.home.pojo.Services;
import com.example.serviceapp.home.ui.home.repository.ServiceRepository;

import java.util.List;

public class ServiceViewModel extends ViewModel {
    public ServiceRepository serviceRepository;

    public ServiceViewModel() {
        serviceRepository=new ServiceRepository();
    }

    public LiveData<CategoryResponse> getService(){
        return serviceRepository.getCategories();
    }
}
