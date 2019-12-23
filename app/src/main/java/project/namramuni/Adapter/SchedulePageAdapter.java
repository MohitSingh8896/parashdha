package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import project.namramuni.Activity.MainActivity;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;

import static com.google.api.client.util.Preconditions.checkArgument;


public class SchedulePageAdapter extends PagerAdapter {

    private Activity activity;
    List<EventList> imagesArray;
    public SchedulePageAdapter(Context activity, List<EventList> imagesArray){
        this.activity = (Activity) activity;
        this.imagesArray = imagesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.view_event_adapter, container, false);
        TextView textTitle = (TextView) viewItem.findViewById(R.id.textTitle);
        TextView textSubTitle = (TextView) viewItem.findViewById(R.id.textSubTitle);
        CardView mohit_schulecardview = (CardView) viewItem.findViewById(R.id.mohit_schulecardview);
        mohit_schulecardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) activity).switchFragment(R.id.navigation_gurudev, 3);
            }
        });



        try {

            String monthconvert="";
            String timeformet="";
            String timeformetend="";
            String sec_startdate =  imagesArray.get(position).getStart_date();
            String sec_startdate1 =  imagesArray.get(position).getEnd_date();



            String[] item1 = sec_startdate.split("-");
            String date1 = item1[0];

            String[] item2 = sec_startdate1.split("-");
            String date2 = item2[0];
            String date3 = item2[1];





            if (date3.equals("01")){
                monthconvert ="January";
            }else if (date3.equals("02")){
                monthconvert ="February";
            }else if (date3.equals("03")){
                monthconvert ="March";
            }else if (date3.equals("04")){
                monthconvert ="April";
            }else if (date3.equals("05")){
                monthconvert ="May";
            }else if (date3.equals("06")){
                monthconvert ="June";
            }else if (date3.equals("07")){
                monthconvert ="July";
            }else if (date3.equals("08")){
                monthconvert ="August";
            }else if (date3.equals("09")){
                monthconvert ="September";
            }else if (date3.equals("10")){
                monthconvert ="October";
            }else if (date3.equals("11")){
                monthconvert ="November";
            }else if (date3.equals("12")){
                monthconvert ="December";
            }


            if(sec_startdate !=null){

                textSubTitle.setText("Date: "+date1+"'"+getDayOfMonthSuffix(Integer.parseInt(date1))+" - "+date2+"'"+getDayOfMonthSuffix(Integer.parseInt(date2))+" "+monthconvert);

            }else {

               textSubTitle.setText("");

            }




        } catch (Exception e) { }


        textTitle.setText(imagesArray.get(position).getEvent_title());


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