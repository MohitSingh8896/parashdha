package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.ActivityBoxViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.ActivityBoxList;

public class ActivityBoxAdapter extends RecyclerView.Adapter<ActivityBoxViewHolder> {
    Context context;
    List<ActivityBoxList> list;
    public ActivityBoxAdapter(Context context, List<ActivityBoxList> imageId1) {
        this.context = context;
        this.list = imageId1;
    }

    @Override
    public ActivityBoxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_activitybox_list, parent, false);
        return new ActivityBoxViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ActivityBoxViewHolder holder, int position) {
        try {
            if (list.get(position).getActi_image() != null) {
                holder.imgIcon.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);
                Picasso.with(context).load(list.get(position).getActi_image()).into(holder.imgIcon, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                        holder.imgIcon.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onError() {
                        holder.imgIcon.setVisibility(View.GONE);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }
                });
            } else {
                holder.imgIcon.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);
            }
            holder.textTitle.setText(list.get(position).getActi_title());
            SetFont.setFont(holder.textTitle,context,SetFont.bold);
        } catch (Exception e) { }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
