package com.example.serviceapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivityNewsBinding;
import com.example.serviceapp.home.ui.home.adapter.NewsAdapter;
import com.example.serviceapp.home.ui.home.viewmodel.NewsViewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

public class NewsActivity extends AppCompatActivity {
 public ActivityNewsBinding newsBinding;
 public NewsViewModel newsViewModel;
 public NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsBinding= DataBindingUtil.setContentView(this,R.layout.activity_news);

        newsViewModel= new ViewModelProvider(this).get(NewsViewModel.class);

        newsBinding.recyclerNews.setLayoutManager(new LinearLayoutManager(this));
        newsBinding.recyclerNews.setHasFixedSize(true);

        newsBinding.layoutBase.toolbar.setTitle("News");

        newsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        newsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });


        getNews();



    }

    private void getNews() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            newsViewModel.getNews().observe(this,newsResponse  -> {
                if (newsResponse != null && newsResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    newsAdapter=new NewsAdapter(this,newsResponse.getNews());
                    newsBinding.recyclerNews.setAdapter(newsAdapter);
                }

            });
        }

    }
}