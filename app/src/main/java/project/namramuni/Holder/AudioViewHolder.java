package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class AudioViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView ImageView;
    public TextView timeDuration,txtTitle;
    public LinearLayout space;
    public AudioViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardview);
        timeDuration = itemView.findViewById(R.id.timeDuration);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        ImageView = itemView.findViewById(R.id.imgVideoAlbum);
        space = itemView.findViewById(R.id.space);
    }
}
