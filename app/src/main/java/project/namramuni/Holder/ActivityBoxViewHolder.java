package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class ActivityBoxViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgIcon;
    public LinearLayout lnr;
    public ProgressBar progressBar;
    public TextView textTitle;
    public ActivityBoxViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.txtTitle);
        imgIcon = itemView.findViewById(R.id.imgIcon);
        progressBar = itemView.findViewById(R.id.progressBar);
        lnr = itemView.findViewById(R.id.lnr);
    }
}
