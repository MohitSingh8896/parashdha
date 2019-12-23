package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Activity.MainActivity;
import project.namramuni.Fragement.FragmentShibir;
import project.namramuni.Holder.ReadViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;

import static com.google.api.client.util.Preconditions.checkArgument;

public class ShibirAdapter extends RecyclerView.Adapter<ReadViewHolder> {
    private Context context;
    private List<EventList> list;
    int s;

    public ShibirAdapter(Context context, List<EventList> list) {
        this.list = list;
        this.context = context;
        this.s = s;
    }

    @NonNull
    @Override
    public ReadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_shibir_list, parent, false);
        return new ReadViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReadViewHolder holder, int position) {
        EventList eventList = list.get(position);
        String upperString = eventList.getShibir_title().substring(0,1).toUpperCase() + eventList.getShibir_title().substring(1);
        try {
            String datetime1 = eventList.getEnd_to();
            String datetime = eventList.getStart_from();

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
                    strDate = d3 + "'" + getDayOfMonthSuffix(Integer.parseInt(d3)) + "-"+d2 + monthFormat1.format(monthwise);
                    holder.textSubTitle.setText("Date: " + strDate);
                } else {
                    strDate = d3 + "'" + getDayOfMonthSuffix(Integer.parseInt(d3)) + " - " + d13 + "'" + getDayOfMonthSuffix(Integer.parseInt(d13)) + " " + monthFormat1.format(monthwise);
                    holder.textSubTitle.setText("Date: " + strDate);
                }
                eventList.setDate(strDate);
            } else {
                Date monthwise = monthFormat.parse(d2);
                Date monthwise1 = monthFormat.parse(d12);
                holder.textSubTitle.setText("Date: " + d3 + getDayOfMonthSuffix(Integer.parseInt(d3)) + " " + monthFormat1.format(monthwise) + " - " + d13 + getDayOfMonthSuffix(Integer.parseInt(d13)) + " " + monthFormat1.format(monthwise1));
            }
            holder.textAuthor.setText("Venue: " + eventList.getShibir_venue());

        } catch (Exception e) {
        }

        holder.textTitle.setText(upperString);

        if (eventList.getShibir_attend().equals("N")){

            holder.shibir_joinbutton.setText("Join Now");
            holder.shibir_joinbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).showFragment2(new FragmentShibir(), list.get(position));
                }
            });

        }else  if (eventList.getShibir_attend().equals("Y")) {

            holder.shibir_joinbutton.setText("Joined");
        }

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
    public int getItemCount() {
        return list.size();
    }
}
