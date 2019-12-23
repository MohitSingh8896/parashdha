package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView titleHeading,txtPhone,txtsubTitle;
    public ImageView img1;
    public LinearLayout lnrPhone,lnrAddress;
    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        lnrAddress = itemView.findViewById(R.id.lnrAddress);
        lnrPhone = itemView.findViewById(R.id.lnrPhone);
        titleHeading = itemView.findViewById(R.id.titleHeading);
        txtsubTitle = itemView.findViewById(R.id.txtsubTitle);
        txtPhone = itemView.findViewById(R.id.txtPhone);
    }
}
