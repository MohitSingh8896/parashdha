package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class AboutViewHolder extends RecyclerView.ViewHolder {
    public TextView titleHeading,txtPhone;
    public ImageView img1;
    public RelativeLayout rlv;
    public AboutViewHolder(@NonNull View itemView) {
        super(itemView);
        rlv = itemView.findViewById(R.id.rlv);
        titleHeading = itemView.findViewById(R.id.titleHeading);
        img1 = itemView.findViewById(R.id.img1);
        txtPhone = itemView.findViewById(R.id.txtPhone);
    }
}
