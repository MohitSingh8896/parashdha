package project.namramuni.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import project.namramuni.R;
import project.namramuni.ViewModels.List.Gallerylist;

/**
 * Created by ADMIN on 28-03-2018.
 */

public class ImageSliderAdapter extends PagerAdapter {
    Context activity;
    List<Gallerylist> storeImages;
    public ImageSliderAdapter(Context activity, List<Gallerylist> storeImages) {
        this.activity = activity;
        this.storeImages = storeImages;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_full_screen_image, null);
        ImageView photoView = view.findViewById(R.id.imgDisplay);
        try {
            Picasso.with(activity).load(storeImages.get(position).getSub_image()).placeholder(R.drawable.logo).into(photoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    public Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public int getCount() {
            return storeImages.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
