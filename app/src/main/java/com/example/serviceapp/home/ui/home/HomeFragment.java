package com.example.serviceapp.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.serviceapp.R;
import com.example.serviceapp.databinding.FragmentHomeBinding;
import com.example.serviceapp.home.adapter.ImageSliderAdapter;
import com.example.serviceapp.home.ui.home.adapter.ServiceAdapter;
import com.example.serviceapp.home.ui.home.pojo.BannerResponse;
import com.example.serviceapp.home.ui.home.pojo.CategoryResponse;
import com.example.serviceapp.home.ui.home.viewmodel.BannerViewModel;
import com.example.serviceapp.home.ui.home.viewmodel.ServiceViewModel;
import com.example.serviceapp.util.Constants;
import com.example.serviceapp.util.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
  public FragmentHomeBinding homeBinding;
    private HomeViewModel homeViewModel;
    public ServiceAdapter serviceAdapter;
    public ServiceViewModel serviceViewModel;
    public BannerViewModel bannerViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        serviceViewModel =
                new ViewModelProvider(this).get(ServiceViewModel.class);

        bannerViewModel =
                new ViewModelProvider(this).get(BannerViewModel.class);

        homeBinding.recyclerServices.setLayoutManager(new GridLayoutManager(getActivity(),3));
        homeBinding.recyclerServices.setHasFixedSize(true);


        setValuesToFields();

        getServices();
        return homeBinding.getRoot();
    }

    private void setValuesToFields() {
        //banner image

        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            bannerViewModel.getBanners().observe(getActivity(),bannerResponse -> {
               // List<BannerResponse.Banners> bannerList = new ArrayList<>();

                homeBinding.rlBanner.setVisibility(View.VISIBLE);
                homeBinding.vpImage.setAdapter(new ImageSliderAdapter(getActivity(), bannerResponse.getBanners()));

                //homeBinding.cpImage.setViewPager(homeBinding.vpImage);

                final float density = getResources().getDisplayMetrics().density;

                //Set circle indicator radius
                //homeBinding.cpImage.setRadius(5 * density);

                homeBinding.vpImage.startAutoScroll();
                homeBinding.vpImage.setInterval(5000);
                homeBinding.vpImage.setCycle(true);
                homeBinding.vpImage.setStopScrollWhenTouch(true);

                // Pager listener over indicator
//               // homeBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                    }
//
//                    @Override
//                    public void onPageScrolled(int pos, float arg1, int arg2) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int pos) {
//
//                    }
//                });
           });

        }

    }

    public void getServices(){
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
         serviceViewModel.getService().observe(getActivity(),categoryResponse -> {
          if (categoryResponse !=null && categoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
              serviceAdapter=new ServiceAdapter(getActivity(), categoryResponse.getCategories());
              homeBinding.recyclerServices.setAdapter(serviceAdapter);
          }
         });
        }
    }
}