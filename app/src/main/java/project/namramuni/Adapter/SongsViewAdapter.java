package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.Activity.PlaylistActivity;
import project.namramuni.Holder.SongsViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.VideoList;

public class SongsViewAdapter extends RecyclerView.Adapter<SongsViewHolder> {
    private Context context;
    ImageView img;
    List<VideoList> bannerlist;
    public SongsViewAdapter(Context context, List<VideoList> bannerlist, ImageView img) {
        this.context = context;
        this.bannerlist=bannerlist;
        this.img = img;
    }
    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_songs_list, parent, false);
        return new SongsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, final int position) {
        Picasso.with(context).load(bannerlist.get(position).getPlaylist_image()).into(holder.ImageView);
        holder.txtTitle.setText(bannerlist.get(position).getPlaylist_name());
        holder.timeDuration.setText(String.valueOf(bannerlist.get(position).getTotal()));
        holder.root_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BhaktilistActivity.class)
                        .putExtra("id", bannerlist.get(position).getId())
                        .putExtra("img", bannerlist.get(position).getPlaylist_image())
                        .putExtra("name", bannerlist.get(position).getPlaylist_name())
                        .putExtra("type", "Bhakti"));
//                Bitmap resultBmp = BlurBuilder.blur(context,bannerlist.get(position).getPlaylist_image());
//                img.setImageBitmap(resultBmp);
            }
        });
    }
    @Override
    public int getItemCount() {
        return bannerlist.size();
    }
}