package com.example.serviceapp.home.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.LayoutItemsBinding;
import com.example.serviceapp.home.ui.home.pojo.ItemResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    public Context context;
    public List<ItemResponse.Datas> datasList;

    public ItemsAdapter(Context context, List<ItemResponse.Datas> datasList) {
        this.context = context;
        this.datasList = datasList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemsBinding itemsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_items,parent,false);
        return new ItemViewHolder(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
     ItemResponse.Datas datas=datasList.get(position);
     holder.itemsBinding.setItems(datas);
    }

    @Override
    public int getItemCount() {
        return datasList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public LayoutItemsBinding itemsBinding;
        public ItemViewHolder(@NonNull LayoutItemsBinding itemsBinding) {
            super(itemsBinding.getRoot());
            this.itemsBinding=itemsBinding;
        }
    }
}
