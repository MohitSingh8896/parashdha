package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class DonateViewHolder extends RecyclerView.ViewHolder {
    public ImageView ImageView,isSelection;
    public TextView textTitle;
    public RelativeLayout rlvDonat;
    public DonateViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.textTitle);
        isSelection = itemView.findViewById(R.id.isSelection);
        rlvDonat = itemView.findViewById(R.id.rlvDonat);
        ImageView = itemView.findViewById(R.id.imgBook);
    }
}
