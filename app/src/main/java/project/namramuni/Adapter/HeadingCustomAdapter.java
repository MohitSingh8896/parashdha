package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import project.namramuni.Activity.QuoteActivity;
import project.namramuni.R;
import project.namramuni.ViewModels.List.Quotelist;


public class HeadingCustomAdapter extends PagerAdapter {

    private Activity activity;
    List<Quotelist> imagesArray;
    public HeadingCustomAdapter(Activity activity, List<Quotelist> imagesArray){
        this.activity = activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.heading, container, false);

        TextView txtHeadline = (TextView) viewItem.findViewById(R.id.titleHeading);
        RelativeLayout rlv = (RelativeLayout) viewItem.findViewById(R.id.rlv);

        txtHeadline.setText(imagesArray.get(position).getDonate_title().substring(0,1).toUpperCase() + imagesArray.get(position).getDonate_title().substring(1));
        rlv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.startActivity(new Intent(activity,QuoteActivity.class).putExtra("text",
//                        imagesArray.get(position).getDonate_title()).putExtra("image",
//                        imagesArray.get(position).getDonate_image()));
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