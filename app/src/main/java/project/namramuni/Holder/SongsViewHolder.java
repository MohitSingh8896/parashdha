package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class SongsViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView ImageView;
    public TextView txtTitle,timeDuration;
    public RelativeLayout root_container;
    public SongsViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardview);
        ImageView = itemView.findViewById(R.id.pagerImg);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        timeDuration = itemView.findViewById(R.id.timeDuration);
        root_container = itemView.findViewById(R.id.root_container);
    }
}
