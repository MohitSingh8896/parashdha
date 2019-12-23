package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import project.namramuni.R;
import project.namramuni.ViewModels.List.Bannerlist;


public class CustomAdapter extends PagerAdapter {

    private Activity activity;
    List<Bannerlist> imagesArray;
    public CustomAdapter(Activity activity, List<Bannerlist> imagesArray){
        this.activity = activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.banner, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.img);
//        TextView subtitle = (TextView) viewItem.findViewById(R.id.subtitle);
//        subtitle.setText(imagesArray.get(position).getSubtitle());
        Picasso.with(activity).load(imagesArray.get(position).getBanner_img()).into(imageView);
        /*subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numLines = subtitle.getLineCount();
                Toast.makeText(activity, String.valueOf(numLines), Toast.LENGTH_SHORT).show();
            }
        });*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(imagesArray.get(position).getBanner_link()));
//                activity.startActivity(i);
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