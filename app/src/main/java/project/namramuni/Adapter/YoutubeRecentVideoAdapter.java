package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.namramuni.Activity.LiveYoutubeActivity;
import project.namramuni.Activity.PlaylistActivity;
import project.namramuni.Holder.YoutubeViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.List.VideoList;

import static project.namramuni.Application.getContext;

public class YoutubeRecentVideoAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private static final String TAG = YoutubeRecentVideoAdapter.class.getSimpleName();
    private Context context;
    List<RecentVideoList> videolist;

    public YoutubeRecentVideoAdapter(Context context, List<RecentVideoList> videolist) {
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
            holder.txtTitle.setText(videolist.get(position).getFile_title());
            Picasso.with(context).load(videolist.get(position).getFile_img()).into(holder.imgVideoAlbum);

            holder.imgVideoAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent= new Intent(context, LiveYoutubeActivity.class);
                    intent .putExtra("liveurl", videolist.get(position).getFile_url());
                    intent .putExtra("liveStatus",true);
                    context.startActivity(intent);

                }
            });
            SetFont.setFont(holder.timeDuration, context, SetFont.bold);
        }
        holder.timeDuration.setVisibility(View.GONE);
        holder.imgcount.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return videolist.size() + 1;
    }
}
