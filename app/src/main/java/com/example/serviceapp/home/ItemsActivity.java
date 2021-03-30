package com.example.serviceapp.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivityItemsBinding;
import com.example.serviceapp.home.ui.home.AddDataActivity;
import com.example.serviceapp.home.ui.home.adapter.ItemsAdapter;
import com.example.serviceapp.home.ui.home.viewmodel.ItemviewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

import java.io.IOException;

import static android.content.Intent.ACTION_GET_CONTENT;
import static android.content.Intent.createChooser;
import static android.provider.MediaStore.Images.Media.getBitmap;
import static android.view.View.VISIBLE;
import static com.bumptech.glide.Glide.with;
import static com.example.serviceapp.util.Constants.OPEN_GALLERY_REQUEST_CODE;
import static com.example.serviceapp.util.Constants.PROFILE_IMAGE;
import static java.util.Objects.requireNonNull;

public class ItemsActivity extends AppCompatActivity {
 public ActivityItemsBinding itemsBinding;
 public ItemviewModel itemviewModel;
 public ItemsAdapter itemsAdapter;
 public String sub_categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsBinding= DataBindingUtil.setContentView(this,R.layout.activity_items);

        itemviewModel=new ViewModelProvider(this).get(ItemviewModel.class);

        itemsBinding.recyclerItems.setLayoutManager(new LinearLayoutManager(this));
        itemsBinding.recyclerItems.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        itemsBinding.recyclerItems.setHasFixedSize(true);

        sub_categoryId=getIntent().getStringExtra("subcategory_id");

        itemsBinding.layoutBase.toolbar.setTitle("Items");

        itemsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        itemsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });

        itemsBinding.textAdd.setOnClickListener(v -> {
            Intent intent=new Intent(ItemsActivity.this, AddDataActivity.class);
            intent.putExtra("subcategory_id",sub_categoryId);
            startActivity(intent);
        });



        getitems();
    }

    private void getitems() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            itemviewModel.getItems(sub_categoryId).observe(this,itemResponse -> {
                if (itemResponse != null && itemResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    itemsAdapter=new ItemsAdapter(this,itemResponse.getData());
                    itemsBinding.recyclerItems.setAdapter(itemsAdapter);
                }

            });
        }
    }







}