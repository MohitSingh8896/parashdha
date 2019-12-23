package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.AudioViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.PDFList;

public class ArticleViewAdapter extends RecyclerView.Adapter<AudioViewHolder> {
    private static final String TAG = AudioViewAdapter.class.getSimpleName();
    private Context context;
    List<PDFList> imageId1;
    public ArticleViewAdapter(Context context, List<PDFList> imageId1) {
        this.context = context;
        this.imageId1 = imageId1;
    }

    @Override
    public AudioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_raticle_list, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        try {
            Picasso.with(context).load(imageId1.get(position).getFile_img()).into(holder.ImageView);
        }catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        return imageId1.size();
    }
}