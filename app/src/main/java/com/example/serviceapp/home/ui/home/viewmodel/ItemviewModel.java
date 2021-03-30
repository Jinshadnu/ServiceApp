package com.example.serviceapp.home.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.home.pojo.CommonResponse;
import com.example.serviceapp.home.ui.home.pojo.ItemResponse;
import com.example.serviceapp.home.ui.home.repository.ItemRepository;

public class ItemviewModel extends ViewModel {
    public ItemRepository itemRepository;

    public ItemviewModel() {
        this.itemRepository=new ItemRepository();
    }

    public LiveData<ItemResponse> getItems(String sub_category_id){
        return itemRepository.getItems(sub_category_id);
    }

    public LiveData<CommonResponse> addItems(String name,String phone,String place,String subcategory_id,String image){
        return itemRepository.addItems(name,phone,place,subcategory_id,image);
    }
}
