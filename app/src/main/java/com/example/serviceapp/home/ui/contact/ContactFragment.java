package com.example.serviceapp.home.ui.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.FragmentContactBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
 public FragmentContactBinding contactBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_contact, container, false);

        contactBinding.textViewCall.setOnClickListener(v -> {
            Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.CALL_PHONE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "7994648430"));
                            startActivity(intent);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            // check for permanent denial of permission
                            if (response.isPermanentlyDenied()) {
                                // navigate user to app settings
                                Toast.makeText(getContext(), "We need CALL_PHONE permission to call from your phone.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();

            contactBinding.textViewWhatsapp.setOnClickListener(v1 -> {
                String number="+91 7994648430";
                String text="Hello";
                boolean installed=isAppInstalled("com.whatsapp");

                if (installed){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+number+"&text="+ text));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),"Whatsapp is not Installed",Toast
                    .LENGTH_LONG).show();

                }
            });

            contactBinding.textViewWebsite.setOnClickListener(v1 -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://http://www.vingcoz.com/"));
                startActivity(browserIntent);
            });

        });

        return contactBinding.getRoot();

    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager=getActivity().getPackageManager();
        boolean is_installed;
        try {
           packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
           is_installed=true;
        }catch (PackageManager.NameNotFoundException e){
            is_installed=false;
            e.printStackTrace();

        }
        return is_installed;
        }
}