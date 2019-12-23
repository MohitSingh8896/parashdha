package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Activity.PlaylistActivity;
import project.namramuni.Holder.YoutubeViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.List.VideoList;

public class YoutubeVideoViewAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoViewAdapter.class.getSimpleName();
    private Context context;
    List<RecentVideoList> videolist;

    public YoutubeVideoViewAdapter(Context context, List<RecentVideoList> videolist) {
        this.context = context;
        this.videolist = videolist;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_video_list, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {
        if (position == (videolist.size())) {
            holder.space.setVisibility(View.VISIBLE);
            holder.content.setVisibility(View.GONE);
        } else {
            holder.txtTitle.setText(videolist.get(position).getTitle());
            Picasso.with(context).load(videolist.get(position).getUrl()).into(holder.imgVideoAlbum);
            holder.timeDuration.setText(String.valueOf(videolist.get(position).getTotalResults()));
            SetFont.setFont(holder.timeDuration, context, SetFont.bold);


            holder.imgVideoAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent videolistmain = new Intent(context, PlaylistActivity.class);
                    videolistmain. putExtra("id", videolist.get(position).getId());
                    videolistmain.putExtra("name", videolist.get(position).getTitle());
                    videolistmain.putExtra("img", videolist.get(position).getUrl());
                    context.startActivity(videolistmain);

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return videolist.size() + 1;
    }
}
