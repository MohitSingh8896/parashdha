package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTitle,track;
    public ImageView imgVideoAlbum,imgSelect;
    public CardView cardview;
    public RelativeLayout root_container;
    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        root_container = itemView.findViewById(R.id.root_container);
        track = itemView.findViewById(R.id.timeDuration);
        imgVideoAlbum = itemView.findViewById(R.id.imgVideoAlbum);
        imgSelect = itemView.findViewById(R.id.imgSelect);
        cardview = itemView.findViewById(R.id.cardview);
    }
}
