package project.namramuni.UIDesign;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import project.namramuni.R;

public class Loading extends AppCompatActivity {
    public static void Visibility(View view) {
        view.findViewById(R.id.rlvLoad).setVisibility(View.VISIBLE);
        view.findViewById(R.id.relative).setVisibility(View.GONE);
    }

    public static void Visibility(Context context, View view, int i) {
        TextView textViewTitle = view.findViewById(R.id.txtNoInternetTitle);
        TextView textViewSubTitle = view.findViewById(R.id.txtNoInternetSubTitle);
        ImageView imageView = (ImageView) view.findViewById(R.id.setImage);
        RelativeLayout load = view.findViewById(R.id.rlvLoad);
        RelativeLayout relative = view.findViewById(R.id.relative);
        LinearLayout nointernet = view.findViewById(R.id.lnrNonet);
        if (i == 1) {               //          Success
            relative.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
        } else if (i == 404) {      //        No internet
            nointernet.setVisibility(View.VISIBLE);
            view.findViewById(R.id.rlvLoad).setVisibility(View.VISIBLE);
            view.findViewById(R.id.relative).setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.noconncetion).into(imageView);
            textViewTitle.setText("Opps, your connection seems off");
            textViewSubTitle.setText("Keep calm, light a fire and pull to refresh to try again.");
        } else if (i == 500) {      //        Server Error
            nointernet.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.servererror).into(imageView);
            textViewTitle.setText("You have internal server error.");
            textViewSubTitle.setText("there was a problem connecting to the server. Please try again.");
        } else {
            nointernet.setVisibility(View.GONE);
            load.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
        }
    }

    public static void Visibility(Context context, View view, String txt) {
        TextView txtNoData = view.findViewById(R.id.txtNoData);
        RelativeLayout load = view.findViewById(R.id.rlvLoad);
        RelativeLayout relative = view.findViewById(R.id.relative);
        LinearLayout nointernet = view.findViewById(R.id.lnrNonet);
        nointernet.setVisibility(View.GONE);
        load.setVisibility(View.GONE);
        relative.setVisibility(View.VISIBLE);
        txtNoData.setText("Coming soon");
    }
    public static void Visibility1(Context context, View view, String txt) {
        TextView txtNoData = view.findViewById(R.id.txtNoData);
        RelativeLayout load = view.findViewById(R.id.rlvLoad);
        RelativeLayout relative = view.findViewById(R.id.relative);
        LinearLayout nointernet = view.findViewById(R.id.lnrNonet);
        nointernet.setVisibility(View.GONE);
        load.setVisibility(View.GONE);
        relative.setVisibility(View.GONE);
        txtNoData.setVisibility(View.VISIBLE);
        txtNoData.setText("Coming soon");
    }
    public static void Visibility(Activity activity) {
        ((Activity) activity).findViewById(R.id.rlvLoad).setVisibility(View.VISIBLE);
        ((Activity) activity).findViewById(R.id.relative).setVisibility(View.GONE);
    }

    public static void Visibility(Context context, int i) {
        TextView textViewTitle = ((Activity) context).findViewById(R.id.txtNoInternetTitle);
        TextView textViewSubTitle = ((Activity) context).findViewById(R.id.txtNoInternetSubTitle);
        ImageView imageView = ((Activity) context).findViewById(R.id.setImage);
        RelativeLayout load = ((Activity) context).findViewById(R.id.rlvLoad);
        RelativeLayout relative = ((Activity) context).findViewById(R.id.relative);
        LinearLayout nointernet = ((Activity) context).findViewById(R.id.lnrNonet);
        if (i == 1) {               //          Success
            load.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
        } else if (i == 404) {      //        No internet
            nointernet.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.noconncetion).into(imageView);
            textViewTitle.setText("Opps, your connection seems off");
            textViewSubTitle.setText("Keep calm, light a fire and pull to refresh to try again.");
        } else if (i == 500) {      //        Server Error
            nointernet.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.servererror).into(imageView);
            textViewTitle.setText("You have internal server error.");
            textViewSubTitle.setText("there was a problem connecting to the server. Please try again.");
        } else {
            nointernet.setVisibility(View.GONE);
            load.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
        }
    }
    /*public static void Visibility1(Activity activity) {
        ((Activity) activity).findViewById(R.id.rlvLoad1).setVisibility(View.VISIBLE);
        ((Activity) activity).findViewById(R.id.relative1).setVisibility(View.GONE);
    }
    public static void Visibility1(Context context,int i) {
        TextView textViewTitle = ((Activity) context).findViewById(R.id.txtNoInternetTitle1);
        TextView textViewSubTitle = ((Activity) context).findViewById(R.id.txtNoInternetSubTitle1);
        ImageView imageView = ((Activity) context).findViewById(R.id.setImage1);
        RelativeLayout load = ((Activity) context).findViewById(R.id.rlvLoad1);
        RelativeLayout relative = ((Activity) context).findViewById(R.id.relative1);
        LinearLayout nointernet = ((Activity) context).findViewById(R.id.lnrNonet1);
        if (i == 1) {               //          Success
            load.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
        } else if (i == 404) {      //        No internet
            nointernet.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.noconncetion).into(imageView);
            textViewTitle.setText("Opps, your connection seems off");
            textViewSubTitle.setText("Keep calm, light a fire and pull to refresh to try again.");
        } else if (i == 500) {      //        Server Error
            nointernet.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            Picasso.with(context).load(R.drawable.servererror).into(imageView);
            textViewTitle.setText("You have internal server error.");
            textViewSubTitle.setText("there was a problem connecting to the server. Please try again.");
        } else {
            nointernet.setVisibility(View.GONE);
            load.setVisibility(View.GONE);
            relative.setVisibility(View.VISIBLE);
        }
    }*/
}
