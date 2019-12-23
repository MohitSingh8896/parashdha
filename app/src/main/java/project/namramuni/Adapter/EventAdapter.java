package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Activity.MainActivity;
import project.namramuni.Fragement.FragmentEvent;
import project.namramuni.Holder.ReadViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;

import static com.google.api.client.util.Preconditions.checkArgument;

public class EventAdapter extends RecyclerView.Adapter<ReadViewHolder> {
    private Context context;
    private List<EventList> list;
    int s;

    public EventAdapter(Context context, List<EventList> list) {
        this.list = list;
        this.context = context;
        this.s = s;
    }

    @NonNull
    @Override
    public ReadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_event_list, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadViewHolder holder, int position) {
        try {

             String timeformet="";
             String timeformetend="";
             String sec_starttime =  list.get(position).getStart_time();
             String sec_starttime1 =  list.get(position).getEnd_time();
             String[] timeitem = sec_starttime.split(" ");
             String timeam = timeitem[0];
             String timeam1 = timeitem[1];//
             String[] timeitem1 = sec_starttime1.split(" ");
             String endtime = timeitem1[0];
             String endtime1 = timeitem1[1];
            String dateStar = list.get(position).getStart_date();;
            String endDate = list.get(position).getEnd_date();

//        Start date
            SimpleDateFormat parseDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat formatdate = new SimpleDateFormat("dd-MMM-yyyy");

//        End date

            SimpleDateFormat parseDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat formatdate1 = new SimpleDateFormat("dd-MMM-yyyy");


                Date date1=parseDateFormat.parse(dateStar);
                Date date2=parseDateFormat.parse(endDate);
                String   datestar = formatdate.format(date1);
                String   dateend = formatdate.format(date2);

                String[] item1 = datestar.split("-");
                String fastdate = item1[0];
                String fastdate1 = item1[1];
                String fastdate2 = item1[2];

                String[] item2 = dateend.split("-");
                String fastdateE = item2[0];
                String fastdateE1 = item2[1];
                String fastdateE2 = item2[2];

                String satrtdateformet = fastdate +"'"+getDayOfMonthSuffix(Integer.parseInt(fastdate));
                String Enddateformet = fastdateE +"'"+getDayOfMonthSuffix(Integer.parseInt(fastdateE))+"-"+fastdateE1+"-"+fastdateE2;



            if (timeam1.equals("AM")){
                timeformet= "am";
            }else if (timeam1.equals("PM")){
                timeformet= "pm";
            }
            if (endtime1.equals("AM")){
                timeformetend= "am";
            }else if (endtime1.equals("PM")){
                timeformetend= "pm";
            }

            if(satrtdateformet !=null & Enddateformet != null){
                holder.textAuthor.setText("Time: "+timeam+" "+timeformet+" - "+endtime+" "+timeformetend);
                holder.textSubTitle.setText("Date: "+satrtdateformet+" - "+ Enddateformet);
//
            }else {
                holder.textAuthor.setText("");
                holder.textSubTitle.setText("");

            }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        String upperString = list.get(position).getEvent_title().substring(0,1).toUpperCase() + list.get(position).getEvent_title().substring(1);
        holder.textTitle.setText(upperString);

        holder.reyclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).showFragment2(new FragmentEvent(), list.get(position));

            }
        });


    }
//
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
    public int getItemCount() {
        return list.size();
    }
}
