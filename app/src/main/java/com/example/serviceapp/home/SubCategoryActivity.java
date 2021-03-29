package com.example.serviceapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivitySubCategoryBinding;
import com.example.serviceapp.home.adapter.BannerAdapter;
import com.example.serviceapp.home.adapter.ImageSliderAdapter;
import com.example.serviceapp.home.adapter.SubCategoryAdapter;
import com.example.serviceapp.home.viewmodel.SubCategoryViewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {
    public ActivitySubCategoryBinding subCategoryBinding;
    public SubCategoryViewModel subCategoryViewModel;
    public SubCategoryAdapter subCategoryAdapter;
    public String category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        category_id=getIntent().getStringExtra("category_id");

        subCategoryBinding= DataBindingUtil.setContentView(this,R.layout.activity_sub_category);

        subCategoryViewModel= new ViewModelProvider(this).get(SubCategoryViewModel.class);

        subCategoryBinding.recyclerSubcategory.setLayoutManager(new GridLayoutManager(this,3));
        subCategoryBinding.recyclerSubcategory.setHasFixedSize(true);

        subCategoryBinding.layoutBase.toolbar.setTitle("Sub Category");

        subCategoryBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        subCategoryBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });

        setValuesToFields();
        getItems();
    }

    private void setValuesToFields() {
        List<String> bannerList = new ArrayList<>();
        bannerList.add("1");
        bannerList.add("2");
        bannerList.add("3");
        bannerList.add("4");
        subCategoryBinding.rlBanner.setVisibility(View.VISIBLE);
        subCategoryBinding.vpImage.setAdapter(new BannerAdapter(this, bannerList));

        subCategoryBinding.cpImage.setViewPager(subCategoryBinding.vpImage);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        subCategoryBinding.cpImage.setRadius(5 * density);

        subCategoryBinding.vpImage.startAutoScroll();
        subCategoryBinding.vpImage.setInterval(5000);
        subCategoryBinding.vpImage.setCycle(true);
        subCategoryBinding.vpImage.setStopScrollWhenTouch(true);

        // Pager listener over indicator
        subCategoryBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void getItems() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){

            subCategoryViewModel.getItems(category_id).observe(this,subCategoryResponse -> {

                if (subCategoryResponse != null && subCategoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    subCategoryAdapter=new SubCategoryAdapter(this,subCategoryResponse.getSub_categories());
                    subCategoryBinding.recyclerSubcategory.setAdapter(subCategoryAdapter);
                }

            });
        }
    }
}