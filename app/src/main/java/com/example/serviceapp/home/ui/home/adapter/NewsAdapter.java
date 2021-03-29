package com.example.serviceapp.home.ui.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.LayoutNewsBinding;
import com.example.serviceapp.home.pojo.News;
import com.example.serviceapp.home.ui.home.pojo.NewsResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVieHolder> {
    public Context context;
    public List<NewsResponse.Newses> newsList;

    public NewsAdapter(Context context, List<NewsResponse.Newses> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutNewsBinding newsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_news,parent,false);
        return new NewsVieHolder(newsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsVieHolder holder, int position) {
     NewsResponse.Newses news=newsList.get(position);
     holder.newsBinding.setNews(news);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsVieHolder extends RecyclerView.ViewHolder {
        public LayoutNewsBinding newsBinding;
        public NewsVieHolder(@NonNull LayoutNewsBinding newsBinding) {
            super(newsBinding.getRoot());
            this.newsBinding=newsBinding;
        }
    }
}
