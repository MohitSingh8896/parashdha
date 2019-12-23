package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kotlin.jvm.internal.CollectionToArray;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Constant.ApplicationData;
import project.namramuni.Fragement.FragmentEvent;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.Quotelist;

import static com.google.api.client.util.Preconditions.checkArgument;


public class EventPageAdapter extends PagerAdapter {

    private Activity activity;
    List<EventList> imagesArray;

    public static Boolean isShibir = false;
    public EventPageAdapter(Context activity, List<EventList> imagesArray){
        this.activity = (Activity) activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.view_shibir_adapter, container, false);
        TextView txtHeadline = (TextView) viewItem.findViewById(R.id.textTitle);
        TextView textSubTitle = (TextView) viewItem.findViewById(R.id.textSubTitle);
        CardView mohit_shibircardview = (CardView) viewItem.findViewById(R.id.mohit_shibircardview);

         mohit_shibircardview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 isShibir = true;
                 ((MainActivity) activity).switchFragment(R.id.navigation_gurudev, 3);


             }
         });


        try {
            String datetime1 = imagesArray.get(position).getEnd_to();//eventList.getEnd_to();
            String datetime = imagesArray.get(position).getStart_from();;//eventList.getStart_from();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            SimpleDateFormat monthFormat1 = new SimpleDateFormat("MMMM");
            String[] day1 = datetime.split("-");
            String d1 = day1[0];
            String d2 = day1[1];
            String d3 = day1[2];
            String[] day2 = datetime1.split("-");
            String d11 = day2[0];
            String d12 = day2[1];
            String d13 = day2[2];
            if (d12.equals(d2)) {
                Date monthwise = monthFormat.parse(d2);
                String strDate = "";
                if (d3.equals(d13)) {
                    strDate = d3 + "'" + getDayOfMonthSuffix(Integer.parseInt(d3)) + " " + monthFormat1.format(monthwise);
                    textSubTitle.setText("Date: " + strDate);
                } else {
                    strDate = d3 + "'" + getDayOfMonthSuffix(Integer.parseInt(d3)) + " - " + d13 + "'" + getDayOfMonthSuffix(Integer.parseInt(d13)) + " " + monthFormat1.format(monthwise);
                    textSubTitle.setText("Date: " + strDate);
                }

            } else {
                Date monthwise = monthFormat.parse(d2);
                Date monthwise1 = monthFormat.parse(d12);
                textSubTitle.setText("Date: " + d3 + getDayOfMonthSuffix(Integer.parseInt(d3)) + " " + monthFormat1.format(monthwise) + " - " + d13 + getDayOfMonthSuffix(Integer.parseInt(d13)) + " " + monthFormat1.format(monthwise1));
            }

        } catch (Exception e) {
        }
        txtHeadline.setText(imagesArray.get(position).getShibir_title());
        ((ViewPager)container).addView(viewItem);
        return viewItem;
    }

    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
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