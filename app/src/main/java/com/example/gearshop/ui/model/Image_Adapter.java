package com.example.gearshop.ui.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gearshop.R;
import com.squareup.picasso.Picasso;


public class Image_Adapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls;

    public Image_Adapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        if(imageUrls[position].length() > 32){
            Picasso.get()
                    .load(imageUrls[position])
                    .into(imageView);
        }else {
            imageView.setImageResource(R.drawable.baseline_help_center_24);
        }

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
