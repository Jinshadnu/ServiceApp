package com.example.serviceapp.home.ui.feedback;

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
import com.example.serviceapp.databinding.FragmentFeedBcakBinding;
import com.example.serviceapp.home.ui.feedback.viewmodel.FeedbackViewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedBcakFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedBcakFragment extends Fragment {
 public FeedbackViewModel feedbackViewModel;
 public FragmentFeedBcakBinding feedBcakBinding;
 public String name,phone,feedback;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedBcakFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedBcakFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedBcakFragment newInstance(String param1, String param2) {
        FeedBcakFragment fragment = new FeedBcakFragment();
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
        feedbackViewModel=new ViewModelProvider((FragmentActivity)this.getActivity()).get(FeedbackViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        feedBcakBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_feed_bcak, container, false);

        feedBcakBinding.buttonSend.setOnClickListener(v -> {
            sendFeedback();
        });
        return feedBcakBinding.getRoot();
    }

    private void sendFeedback() {
        name=feedBcakBinding.edittextName.getText().toString();
        phone=feedBcakBinding.edittextPhone.getText().toString();
        feedback=feedBcakBinding.edittextFeedback.getText().toString();

        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            feedbackViewModel.sendFeedback(name,phone,feedback).observe(getActivity(),commonResponse -> {
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    Toast.makeText(getActivity(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}