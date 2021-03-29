package com.example.serviceapp.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.home.pojo.SubCategoryResponse;
import com.example.serviceapp.home.ui.home.repository.SubCategoryRepository;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCateroryRepository;

    public SubCategoryViewModel() {
        this.subCateroryRepository=new SubCategoryRepository();
    }

    public LiveData<SubCategoryResponse> getItems(String category_id){
        return subCateroryRepository.getSubCategories(category_id);
    }
}
