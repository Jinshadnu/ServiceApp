package com.example.serviceapp.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.LayoutNewsBinding;
import com.example.serviceapp.databinding.LayoutSubcategoryBinding;
import com.example.serviceapp.home.ItemsActivity;
import com.example.serviceapp.home.SubCategoryActivity;
import com.example.serviceapp.home.pojo.SubCategory;
import com.example.serviceapp.home.ui.home.pojo.SubCategoryResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubViewHolder> {
    public Context context;
    public List<SubCategoryResponse.Sub_categories> subCategories;
    public String catgory_id;
    public int postion=0;

    public SubCategoryAdapter(Context context, List<SubCategoryResponse.Sub_categories> subCategories,String catgory_id) {
        this.context = context;
        this.subCategories = subCategories;
        this.catgory_id=catgory_id;
    }



    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubcategoryBinding subcategoryBinding= DataBindingUtil.inflate(from(context), R.layout.layout_subcategory,parent,false);
        return new
                SubViewHolder(subcategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
     SubCategoryResponse.Sub_categories subCategory=subCategories.get(position);
     holder.subcategoryBinding.setSubcategory(subCategory);
     holder.subcategoryBinding.relativeServices.setOnClickListener(v -> {
         Intent intent=new Intent(context.getApplicationContext(), ItemsActivity.class);
         intent.putExtra("subcategory_id",subCategory.getSub_category_id());
         intent.putExtra("category_id",catgory_id);
         postion=holder.getAdapterPosition();
         intent.putExtra("position",postion);
         context.startActivity(intent);
     });
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class SubViewHolder extends RecyclerView.ViewHolder {
        public LayoutSubcategoryBinding subcategoryBinding;
        public SubViewHolder(@NonNull LayoutSubcategoryBinding subcategoryBinding) {
            super(subcategoryBinding.getRoot());
            this.subcategoryBinding=subcategoryBinding;


        }
    }
}
