package com.example.serviceapp.home.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivityAddDataBinding;

public class AddDataActivity extends AppCompatActivity {
 public ActivityAddDataBinding addDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addDataBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_data);

        addDataBinding.layoutBase.toolbar.setTitle("Add Data");

        addDataBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        addDataBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });
    }
}