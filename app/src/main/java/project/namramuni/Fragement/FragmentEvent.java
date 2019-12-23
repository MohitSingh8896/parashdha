package project.namramuni.Fragement;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.MainActivity;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;

import static com.google.api.client.util.Preconditions.checkArgument;

public class FragmentEvent extends Fragment {
    @BindView(R.id.txtTitleName)
    TextView txtTitleName;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.details)
    TextView details;
    @BindView(R.id.imgclose)
    ImageView imgclose;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, null);
        ButterKnife.bind(this, view);
        EventList dene = (EventList) getArguments().getSerializable("response");
        txtTitleName.setText(dene.getEvent_title().substring(0,1).toUpperCase() + dene.getEvent_title().substring(1));

        String dateStar = dene.getStart_date();
        String endDate = dene.getEnd_date();


//        Start date
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formatdate = new SimpleDateFormat("dd-MMM-yyyy");

//        End date

        SimpleDateFormat parseDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formatdate1 = new SimpleDateFormat("dd-MMM-yyyy");


        try {
            Date date1=parseDateFormat.parse(dateStar);
            Date date2=parseDateFormat.parse(endDate);
          //  Log.d("New Date",formatdate.format(date1));
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

            String satrtdateformet = fastdate +"'"+getDayOfMonthSuffix(Integer.parseInt(fastdate))+"-"+fastdate1+"-"+fastdate2;
            String Enddateformet = fastdateE +"'"+getDayOfMonthSuffix(Integer.parseInt(fastdateE))+"-"+fastdateE1+"-"+fastdateE2;

            txtDate.setText(satrtdateformet+" To "+ Enddateformet);


        } catch (ParseException e) {
            e.printStackTrace();
        }





        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).backstackFragment();
            }
        });
        if (dene.getEvent_title()!=null) {
            details.setText(dene.getEvent_description());
        }
        return view;
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

}
