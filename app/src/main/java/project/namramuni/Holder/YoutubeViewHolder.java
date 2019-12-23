package project.namramuni.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class YoutubeViewHolder extends RecyclerView.ViewHolder {
    public YouTubeThumbnailView videoThumbnailImageView;
    public TextView txtTitle,timeDuration;
    public ImageView imgVideoAlbum,imgcount;
    public CardView cardview;
    public LinearLayout content,space;
    public YoutubeViewHolder(@NonNull View itemView) {
        super(itemView);
        videoThumbnailImageView = itemView.findViewById(R.id.videoImage);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        imgVideoAlbum = itemView.findViewById(R.id.imgVideoAlbum);
        imgcount = itemView.findViewById(R.id.imgcount);
        cardview = itemView.findViewById(R.id.cardview);
        timeDuration = itemView.findViewById(R.id.timeDuration);
        space = itemView.findViewById(R.id.space);
        content = itemView.findViewById(R.id.content);
    }
}
