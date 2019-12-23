package project.namramuni.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.PlayList;
public class YoutubeVideoLinkAdapter extends RecyclerView.Adapter<YoutubeVideoLinkAdapter.YoutubeListHolder> {
    private static final String TAG = YoutubeVideoLinkAdapter.class.getSimpleName();
    private Context context;
    private List<PlayList> list;
    ImageView share;
    public String positionSet = "-1";
    public YoutubeVideoLinkAdapter(Activity context, List<PlayList> list, ImageView share) {
        this.list = list;
        this.context = context;
        this.share = share;
    }
    @Override
    public YoutubeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_watch_list, parent, false);
        return new YoutubeListHolder(view);
    }
    public String getDate(String date){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
        try{
            Date fromDate = input.parse(date);
            return output.format(fromDate);
        }catch (Exception e){
            return date;
        }
    }

    @Override
    public void onBindViewHolder(YoutubeListHolder holder, final int position) {
        PlayList item = list.get(position);
        try {
            holder.txtTitle.setText(item.getSong_name());
            holder.timeDuration.setText(getDate(item.getUpload_time()));
            SetFont.setFont(holder.txtTitle, context, SetFont.bold);
            SetFont.setFont(holder.timeDuration, context, SetFont.normal);
            if (positionSet.equals(String.valueOf(position))) {
                holder.cardview.setVisibility(View.GONE);
                holder.txt.setVisibility(View.VISIBLE);
            } else {
                holder.cardview.setVisibility(View.VISIBLE);
                holder.txt.setVisibility(View.GONE);
            }
            holder.videoThumbnailImageView.initialize(list.get(position).getSong_url(), new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    //when initialization is sucess, set the video id to thumbnail to load
                    youTubeThumbnailLoader.setVideo(list.get(position).getSong_url());
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                            youTubeThumbnailLoader.release();
                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                            //print or show error when thumbnail load failed
                            Log.e(TAG, "Youtube Thumbnail Error");
                        }
                    });
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    //print or show error when initialization failed
                    Log.e(TAG, "Youtube Initialization Failure");
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=" + list.get(position).getSong_url());
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Namramuni");
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"));
                }
            });
        } catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class YoutubeListHolder extends RecyclerView.ViewHolder {
        public YouTubeThumbnailView videoThumbnailImageView;
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
