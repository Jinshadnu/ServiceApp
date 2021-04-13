package com.example.serviceapp.home.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivityAddDataBinding;
import com.example.serviceapp.home.ui.home.viewmodel.ItemviewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Intent.ACTION_GET_CONTENT;
import static android.content.Intent.createChooser;
import static android.graphics.Bitmap.CompressFormat.PNG;
import static android.provider.MediaStore.Images.Media.getBitmap;
import static android.text.TextUtils.isEmpty;
import static android.util.Base64.DEFAULT;
import static android.util.Base64.encodeToString;
import static android.view.View.VISIBLE;
import static com.bumptech.glide.Glide.with;
import static com.example.serviceapp.util.Constants.OPEN_GALLERY_REQUEST_CODE;
import static com.example.serviceapp.util.Constants.PROFILE_IMAGE;
import static com.example.serviceapp.util.Constants.STEP_ONE_COMPLETE;
import static com.karumi.dexter.Dexter.withActivity;
import static java.util.Objects.requireNonNull;

public class AddDataActivity extends AppCompatActivity {
 public ActivityAddDataBinding addDataBinding;
    public String uploadType;
    private Bitmap profileImageBitmap;
    public String profileImage;
    public String subcategory_id;
    public String name,place,phone;
    public ItemviewModel itemviewModel;
    public ProgressDialog progressDialog;
    public LayoutInflater li;
    public View layout;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addDataBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_data);

        addDataBinding.layoutBase.toolbar.setTitle("Add Data");

        addDataBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        addDataBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });

        subcategory_id=getIntent().getStringExtra("subcategory_id");

        itemviewModel= new ViewModelProvider(this).get(ItemviewModel.class);

        addDataBinding.textChoose.setOnClickListener(v -> {
            withActivity(this).withPermissions(WRITE_EXTERNAL_STORAGE,CAMERA).withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()){
                        uploadType=PROFILE_IMAGE;
                        openGallery();
                    }
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();
        });

        addDataBinding.button2.setOnClickListener(v -> {
            if(validateField()){
                encodeDocuments();
            }

        });
        li = getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        layout = li.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.text_message));


    }

    public boolean validateField(){
        name=requireNonNull(addDataBinding.edittextName.getText().toString());
        place=requireNonNull(addDataBinding.edittextPlace.getText().toString());
        phone=requireNonNull(addDataBinding.edittextPhone.getText().toString());


        if (isEmpty(name)){
            addDataBinding.edittextName.requestFocus();
            addDataBinding.edittextName.setError("Please enter your name");
            return false;
        }
        if (name.length()<3){
            addDataBinding.edittextName.requestFocus();
            addDataBinding.edittextName.setError("Please enter your name");
            return false;
        }

        if (isEmpty(place)){
            addDataBinding.edittextPlace.requestFocus();
            addDataBinding.edittextPlace.setError("Please enter your place");
            return false;
        }
        if (isEmpty(phone)){
            addDataBinding.edittextPhone.requestFocus();
            addDataBinding.edittextPhone.setError("Please enter your phone number");
            return false;
        }
        if (phone.length()<10){
            addDataBinding.edittextPhone.requestFocus();
            addDataBinding.edittextPhone.setError("Please enter 10 digit phone number");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == OPEN_GALLERY_REQUEST_CODE) {
                if (requireNonNull(data).getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < requireNonNull(mClipData).getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        try {
                            Bitmap images = getBitmap(requireNonNull(this)
                                    .getContentResolver(), uri);
                            placeImage(images);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    try {
                        Bitmap image = getBitmap(requireNonNull(this)
                                .getContentResolver(), mImageUri);
                        placeImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(ACTION_GET_CONTENT);
        startActivityForResult(createChooser(intent, "Select Picture"), OPEN_GALLERY_REQUEST_CODE);
    }
        private void placeImage(Bitmap imageBitmap) {
        switch (uploadType) {
            case PROFILE_IMAGE:
                profileImageBitmap=imageBitmap;
                with(this).load(imageBitmap).into(addDataBinding.imageViewProfile);
                addDataBinding.imageViewProfile.setVisibility(VISIBLE);
                break;
        }
    }

    public String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        image.compress(PNG, 80, byteArray);
        byte[] b = byteArray.toByteArray();
        return encodeToString(b, DEFAULT);
    }
    public void encodeDocuments(){
                profileImage =encodeToBase64(profileImageBitmap);
                if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
                    progressDialog=new ProgressDialog(this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();
                  itemviewModel.addItems(name,phone,place,subcategory_id,profileImage).observe(this,commonResponse -> {
                      progressDialog.dismiss();
                      if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                          //Creating the Toast object
                          Toast toast = new Toast(getApplicationContext());
                          toast.setDuration(Toast.LENGTH_SHORT);
                          toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                          ((TextView) layout.findViewById(R.id.text_message)).setText(commonResponse.getMessage());
                          toast.setView(layout);//setting the view of custom toast layout
                          toast.show();
                          finish();
                      }
                  });
                }

    }





}