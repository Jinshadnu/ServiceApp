package com.example.serviceapp.home.ui.feedback.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.serviceapp.home.ui.feedback.repository.FeedbackRepository;
import com.example.serviceapp.home.ui.home.pojo.CommonResponse;

public class FeedbackViewModel extends ViewModel {
    public FeedbackRepository feedbackRepository;

    public FeedbackViewModel() {
        this.feedbackRepository=new FeedbackRepository();
    }

    public LiveData<CommonResponse> sendFeedback(String name,String phone,String feedback){
        return feedbackRepository.sendfeedback(name, phone, feedback);
    }
}
