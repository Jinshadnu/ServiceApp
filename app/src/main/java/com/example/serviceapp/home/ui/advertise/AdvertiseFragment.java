package com.example.serviceapp.home.ui.advertise;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.FragmentAdvertiseBinding;
import com.example.serviceapp.home.ui.home.viewmodel.AdViewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdvertiseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvertiseFragment extends Fragment {


    public FragmentAdvertiseBinding advertiseBinding;
    public AdViewModel adViewModel;
    public String name,message,phone;
    public Context context;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdvertiseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdvertiseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdvertiseFragment newInstance(String param1, String param2) {
        AdvertiseFragment fragment = new AdvertiseFragment();
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

        adViewModel=new ViewModelProvider((FragmentActivity)this.getActivity()).get(AdViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        advertiseBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_advertise, container, false);

       advertiseBinding.button.setOnClickListener(v -> {
           addAdvertisement();
       });

        return advertiseBinding.getRoot();

    }

    public void addAdvertisement() {
        name=advertiseBinding.edittextName.getText().toString();
        phone=advertiseBinding.edittextPhone.getText().toString();
        message=advertiseBinding.edittextMessage.getText().toString();
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            adViewModel.addAdvertsement(name,phone,message).observe(getActivity(),commonResponse -> {
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    Toast.makeText(getActivity(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}