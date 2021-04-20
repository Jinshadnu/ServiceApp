package com.example.serviceapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivityNewsDetailsBinding;

public class NewsDetailsActivity extends AppCompatActivity {
 public ActivityNewsDetailsBinding newsDetailsBinding;
 public String heading,image,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsDetailsBinding= DataBindingUtil.setContentView(this,R.layout.activity_news_details);

        newsDetailsBinding.layoutBase.toolbar.setTitle("News Details");

        newsDetailsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        newsDetailsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });


        heading=getIntent().getStringExtra("heading");
        description=getIntent().getStringExtra("description");
        image=getIntent().getStringExtra("image");

        newsDetailsBinding.textViewHeading.setText(heading);
        newsDetailsBinding.textViewDescription.setText(description);
        Glide.with(this).load(image).into(newsDetailsBinding.imageViewImage);


    }
}