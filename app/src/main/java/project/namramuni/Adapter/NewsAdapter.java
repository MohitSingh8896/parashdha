package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.namramuni.Activity.QuoteActivity;
import project.namramuni.R;
import project.namramuni.ViewModels.List.Bannerlist;


public class NewsAdapter extends PagerAdapter {
    private Activity activity;
    List<Bannerlist> imagesArray;
    public NewsAdapter(Activity activity, List<Bannerlist> imagesArray){
        this.activity = activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.banner, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.img);
        RelativeLayout rlvBanner = (RelativeLayout) viewItem.findViewById(R.id.rlvBanner);
        Picasso.with(activity).load(imagesArray.get(position).getImage()).placeholder(R.color.grey).into(imageView);
        rlvBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, QuoteActivity.class)
                        .putExtra("title",imagesArray.get(position).getNews_title())
                        .putExtra("text", imagesArray.get(position).getDescrp())
                        .putExtra("image", imagesArray.get(position).getImage())
                        .putExtra("view", imagesArray.get(position).getView_at())
                );
            }
        });
        ((ViewPager)container).addView(viewItem);
        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}