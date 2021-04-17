package com.example.serviceapp.home.ui.home.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.LayoutItemsBinding;
import com.example.serviceapp.home.ui.home.pojo.ItemResponse;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import static android.view.LayoutInflater.from;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    public Context context;
    public List<ItemResponse.Datas> datasList;
    String name,place,phone,pincode;


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
     holder.itemsBinding.relativeItems.setOnClickListener(v -> {
       phone=datasList.get(position).getPhone();
       name=datasList.get(position).getName();
       place=datasList.get(position).getPlace();
       pincode=datasList.get(position).getPincode();
       showErrorSnackBar(name,phone,place);
     });
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

    public void showErrorSnackBar(String name,String phone,String place) {
        View modelBottomSheet = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);
        TextView textView_call=modelBottomSheet.findViewById(R.id.textView_call);
        TextView textView_share=modelBottomSheet.findViewById(R.id.textView_share);
        textView_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity((Activity) context)
                        .withPermission(Manifest.permission.CALL_PHONE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                                context.startActivity(intent);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                // check for permanent denial of permission
                                if (response.isPermanentlyDenied()) {
                                    // navigate user to app settings
                                    Toast.makeText(context.getApplicationContext(), "We need CALL_PHONE permission to call from your phone.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();

            }
        });

        textView_share.setOnClickListener(v -> {
            String shareBody = "Name : " + name + "\n" + "Phone Number: " + phone + "\n" + "Place : " +place + "\n" + "Pincode : " +pincode;
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share to friends");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(sharingIntent, "Share to"));
        });


        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(modelBottomSheet);
        dialog.show();

    }
}
