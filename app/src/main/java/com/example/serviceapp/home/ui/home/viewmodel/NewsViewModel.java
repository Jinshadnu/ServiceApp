package com.example.serviceapp.home.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.home.pojo.NewsResponse;
import com.example.serviceapp.home.ui.home.repository.NewsRepository;

public class NewsViewModel extends ViewModel {
    public NewsRepository newsRepository;

    public NewsViewModel() {
        this.newsRepository=new NewsRepository();
    }

    public LiveData<NewsResponse> getNews(){
        return newsRepository.getNews();
    }


}
