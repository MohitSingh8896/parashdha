package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.DonateViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gallerylist;

public class GalleryAdapter extends RecyclerView.Adapter<DonateViewHolder> {
    Context context;
    List<Gallerylist> list;
    public GalleryAdapter(Context context, List<Gallerylist> imageId1) {
        this.context = context;
        this.list = imageId1;
    }
    @Override
    public DonateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_gallery_list, parent, false);
        return new DonateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonateViewHolder holder, int position) {
        try {
            SetFont.setFont(holder.textTitle,context,SetFont.bold);
            Picasso.with(context).load(list.get(position).getMain_image()).into(holder.ImageView);
            holder.textTitle.setText(list.get(position).getTitle_name());
        } catch (Exception e) { }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
