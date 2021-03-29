package com.example.serviceapp.home.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.serviceapp.R;

import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    private List<String> images;
    private LayoutInflater inflater;
    private Context context;

    public ImageSliderAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.image_slider_adapter, view, false);
        assert imageLayout != null;
        ImageView imageView = imageLayout.findViewById(R.id.iv_image);

        if (position == 0) {
            Glide.with(context)
                    .load(R.drawable.advertisment1)
                    .into(imageView);
        } else if (position == 1) {
            Glide.with(context)
                    .load(R.drawable.advertisment3)
                    .into(imageView);
        }
        else if (position == 2){
            Glide.with(context)
                    .load(R.drawable.advertisment3)
                    .into(imageView);
        }
        else {
            Glide.with(context)
                    .load(R.drawable.advertisment4)
                    .into(imageView);
        }

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
