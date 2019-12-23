package project.namramuni.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.AlbumViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;

public class AlbumViewAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private static final String TAG = AlbumViewAdapter.class.getSimpleName();
    private Activity context;
    private List<Gurudevlist> arrayList;
    int selectedPosition=0;
    TextView title;
    public AlbumViewAdapter(Activity context, List<Gurudevlist> arrayList, TextView title, String pos) {
        this.context = context;
        this.arrayList = arrayList;
        selectedPosition = Integer.parseInt(pos);
        this.title=title;
    }
    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_album_list, parent, false);
        return new AlbumViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AlbumViewHolder holder, final int position) {
        holder.txtTitle.setText(arrayList.get(position).getPlaylist_name());
        holder.track.setText(arrayList.get(position).getTotal());
        Picasso.with(context).load(arrayList.get(position).getPlaylist_image()).into(holder.imgVideoAlbum);
        if (selectedPosition==position){
            holder.imgSelect.setVisibility(View.VISIBLE);
            title.setText(arrayList.get(position).getPlaylist_name());
            SetFont.setFont(holder.txtTitle,context,SetFont.bold);
        } else {
            holder.imgSelect.setVisibility(View.GONE);
            SetFont.setFont(holder.txtTitle,context,SetFont.normal);
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}
