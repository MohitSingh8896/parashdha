package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.List.PlayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.YoutubeListHolder> {
    private static final String TAG = SongsAdapter.class.getSimpleName();
    private Context context;
    private List<PlayList> list;

    public SongsAdapter(Activity context, List<PlayList> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public YoutubeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_bhakti_list, parent, false);
        return new YoutubeListHolder(view);
    }

    public String positionSet = "-1";

    @Override
    public void onBindViewHolder(YoutubeListHolder holder, final int position) {
        PlayList item = list.get(position);
        holder.txtTitle.setText(item.getSong_name());
        holder.timeDuration.setText(item.getUpload_time());
        SetFont.setFont(holder.txtTitle, context, SetFont.bold);
        SetFont.setFont(holder.timeDuration, context, SetFont.normal);
        if (positionSet.equals(String.valueOf(position))) {
            holder.cardview.setVisibility(View.GONE);
            holder.txt.setVisibility(View.VISIBLE);
        } else {
            holder.cardview.setVisibility(View.VISIBLE);
            holder.txt.setVisibility(View.GONE);
        }
        Picasso.with(context).load(list.get(position).getCover_image()).into(holder.videoThumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class YoutubeListHolder extends RecyclerView.ViewHolder {
        public ImageView videoThumbnailImageView;
        public TextView txtTitle, timeDuration;
        public ImageView imgVideoAlbum;
        public CardView cardview;
        public LinearLayout space, txt;

        public YoutubeListHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.videoImage);
            space = itemView.findViewById(R.id.space);
            cardview = itemView.findViewById(R.id.cardview);
            txt = itemView.findViewById(R.id.txt);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgVideoAlbum = itemView.findViewById(R.id.imgVideoAlbum);
            timeDuration = itemView.findViewById(R.id.timeDuration);
        }
    }
}
