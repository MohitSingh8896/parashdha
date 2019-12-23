package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.AudioViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.VideoList;

public class AudioViewAdapter extends RecyclerView.Adapter<AudioViewHolder> {
    private static final String TAG = AudioViewAdapter.class.getSimpleName();
    private Context context;
    //position to check which position is selected
    private int selectedPosition = 0;
    List<VideoList> imageId1;
    public AudioViewAdapter(Context context, List<VideoList> imageId1) {
        this.context = context;
        this.imageId1=imageId1;
    }
    @Override
    public AudioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_audio_list, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AudioViewHolder holder, final int position) {
        if (position == (imageId1.size())) {
            holder.space.setVisibility(View.VISIBLE);
            holder.cardView.setVisibility(View.GONE);
        } else {
            holder.space.setVisibility(View.GONE);
            holder.cardView.setVisibility(View.VISIBLE);
            holder.timeDuration.setText(String.valueOf(imageId1.get(position).getTotal()));
            holder.txtTitle.setText(imageId1.get(position).getPlaylist_name());
            Picasso.with(context).load(imageId1.get(position).getPlaylist_image()).into(holder.ImageView);
        }
    }

    @Override
    public int getItemCount() {
        return imageId1.size()+1;
    }
}
