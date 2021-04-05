package com.example.serviceapp.home.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.home.pojo.CommonResponse;
import com.example.serviceapp.home.ui.home.repository.AddvertisementRepository;

public class AdViewModel extends ViewModel {
    public AddvertisementRepository addvertisementRepository;


    public AdViewModel() {
        this.addvertisementRepository=new AddvertisementRepository();
    }

    public LiveData<CommonResponse> addAdvertsement(String name,String phone,String message){
        return addvertisementRepository.addAdvertsement(name,phone,message);
    }
}
