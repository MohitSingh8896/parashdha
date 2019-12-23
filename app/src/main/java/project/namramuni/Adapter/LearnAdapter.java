package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.ReadViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.LearnList;

public class LearnAdapter extends RecyclerView.Adapter<ReadViewHolder> {
    Context context;
    List<LearnList> list;
    public LearnAdapter(Context context, List<LearnList> imageId1) {
        this.context = context;
        this.list = imageId1;
    }

    @Override
    public ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_learn_list, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadViewHolder holder, int position) {
        try {
            Picasso.with(context).load(list.get(position).getFile_img()).into(holder.ImageView);
            holder.textTitle.setText(list.get(position).getFile_title());
            holder.textSubTitle.setText(list.get(position).getType_name());
            holder.ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
