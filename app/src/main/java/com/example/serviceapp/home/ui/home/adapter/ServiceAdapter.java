package com.example.serviceapp.home.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.LayoutOptionsBinding;
import com.example.serviceapp.home.NewsActivity;
import com.example.serviceapp.home.SubCategoryActivity;
import com.example.serviceapp.home.ui.home.pojo.CategoryResponse;
import com.example.serviceapp.home.ui.home.pojo.Services;

import java.util.Date;
import java.util.List;

import static android.view.LayoutInflater.from;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    public Context context;
    public List<CategoryResponse.Categories> servicesList;
    public LayoutInflater layoutInflater;

    public ServiceAdapter(Context context, List<CategoryResponse.Categories> servicesList) {
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutOptionsBinding optionsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_options,parent,false);
        return new ServiceViewHolder(optionsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
      CategoryResponse.Categories services=servicesList.get(position);
       holder.optionsBinding.setServices(services);
       holder.optionsBinding.relativeServices.setOnClickListener(v -> {
           if (position == 0){
               Intent intent= new Intent(context.getApplicationContext(), NewsActivity.class);
               context.startActivity(intent);
           }
           if(position == 1){
               Intent intent=new Intent(context.getApplicationContext(), SubCategoryActivity.class);
               intent.putExtra("category_id",services.getCategory_id());
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        public LayoutOptionsBinding optionsBinding;
        public ServiceViewHolder(@NonNull LayoutOptionsBinding optionsBinding) {
            super(optionsBinding.getRoot());
            this.optionsBinding=optionsBinding;
        }
    }
}
