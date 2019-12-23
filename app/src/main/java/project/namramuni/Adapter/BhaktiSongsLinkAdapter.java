package project.namramuni.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.List.PlayList;

import static project.namramuni.background.AudioService.shilpasongid;

public class BhaktiSongsLinkAdapter extends RecyclerView.Adapter<BhaktiSongsLinkAdapter.YoutubeListHolder> {
    private static final String TAG = BhaktiSongsLinkAdapter.class.getSimpleName();
    private Context context;
    private List<PlayList> list;
    List<Gurudevlist> videolist;
    ImageView share, imgBackground;
    String type;
    RelativeLayout lnrImage;
    LinearLayout lnrVideo;
    String listName;
    TextView txtVideoName, txtVideoDescription;

    public BhaktiSongsLinkAdapter(Activity context, List<PlayList> list, List<Gurudevlist> videolist, ImageView share, ImageView imgBackground, String type, RelativeLayout lnrImage, LinearLayout lnrVideo, String listName, TextView txtVideoName, TextView txtVideoDescription) {
        this.list = list;
        this.type = type;
        this.videolist = videolist;
        this.context = context;
        this.share = share;
        this.lnrImage = lnrImage;
        this.lnrVideo = lnrVideo;
        this.listName = listName;
        this.imgBackground = imgBackground;
        this.txtVideoName = txtVideoName;
        this.txtVideoDescription = txtVideoDescription;
    }

    @Override
    public YoutubeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_bhakti_list, parent, false);
        return new YoutubeListHolder(view);
    }

    public String positionSet = "-1";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(YoutubeListHolder holder, final int position) {
        /*if (videolist != null) {
            Gurudevlist item = videolist.get(position);
            holder.txtTitle.setText(item.getPlaylist_name());
            holder.timeDuration.setText(item.getCreate_date());
            SetFont.setFont(holder.txtTitle, context, SetFont.bold);
            SetFont.setFont(holder.timeDuration, context, SetFont.normal);
            if (shilpasongid.equals(item.getId())) {
                holder.cardview.setVisibility(View.GONE);
                holder.txt.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                listName = String.valueOf(position);
                initYouTubePlayerView(null, videolist.get(position));
            } else {
                holder.cardview.setVisibility(View.VISIBLE);
                holder.txt.setVisibility(View.GONE);
            }
            Picasso.with(context).load(item.getPlaylist_image()).into(holder.videoThumbnailImageView);
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
        } else {*/
        PlayList item = list.get(position);
        holder.txtTitle.setText(item.getSong_name());
        holder.timeDuration.setText(getDate(item.getUpload_time()));
        SetFont.setFont(holder.txtTitle, context, SetFont.bold);
        SetFont.setFont(holder.timeDuration, context, SetFont.normal);
        if (shilpasongid.equals(item.getId())) {
            holder.cardview.setVisibility(View.GONE);
            holder.txt.setVisibility(View.VISIBLE);
            listName = String.valueOf(position);
            share.setVisibility(View.VISIBLE);
            initYouTubePlayerView(list.get(position), null);
        } else {
            holder.cardview.setVisibility(View.VISIBLE);
            holder.txt.setVisibility(View.GONE);
        }
        Picasso.with(context).load(list.get(position).getCover_image()).into(holder.videoThumbnailImageView);
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
//        }
    }

    public String getDate(String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
        try {
            Date fromDate = input.parse(date);
            return output.format(fromDate);
        } catch (Exception e) {
            return date;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initYouTubePlayerView(final PlayList videoUrl, Gurudevlist gurudevlist) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
//        if (gurudevlist == null) {
        txtVideoName.setText(videoUrl.getSong_name());
        try {
            Date fromDate = input.parse(videoUrl.getUpload_time());
            txtVideoDescription.setText(output.format(fromDate) + "\n\n" + videoUrl.getSong_name());
        } catch (Exception e) {
            txtVideoDescription.setText(videoUrl.getUpload_time() + "\n\n" + videoUrl.getSong_name());
        }
        Picasso.with(context).load(videoUrl.getCover_image()).into(imgBackground);
//            musicView("1", -1, videoUrl.getCover_image(), videoUrl.getTitle(), videoUrl.getSong_url(), videoUrl.getId());
       /* } else {
            txtVideoName.setText(gurudevlist.getPlaylist_name());
            try {
                Date fromDate = input.parse(gurudevlist.getCreate_date());
                txtVideoDescription.setText(output.format(fromDate) + "\n\n" + gurudevlist.getPlaylist_name());
            } catch (Exception e) {
                txtVideoDescription.setText(gurudevlist.getCreate_date() + "\n\n" + gurudevlist.getPlaylist_name());
            }
            Picasso.with(context).load(gurudevlist.getPlaylist_image()).into(imgBackground);
//            musicView("1", -1, gurudevlist.getPlaylist_image(), gurudevlist.getPlaylist_name(), gurudevlist.getPlaylist_url(), gurudevlist.getId());
        }*/
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
