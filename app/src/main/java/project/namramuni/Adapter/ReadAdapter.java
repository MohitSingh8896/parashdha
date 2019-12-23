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
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.SubReadList;

public class ReadAdapter extends RecyclerView.Adapter<ReadViewHolder> {
    Context context;
    List<SubReadList> list;
    public ReadAdapter(Context context, List<SubReadList> filelist) {
        this.context = context;
        this.list = filelist;
    }
    @Override
    public ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_read_list, parent, false);
        return new ReadViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ReadViewHolder holder, int position) {
        try {
            SetFont.setFont(holder.textTitle,context,SetFont.bold);
            SetFont.setFont(holder.textSubTitle,context,SetFont.normal);
            SetFont.setFont(holder.textAuthor,context,SetFont.normal);
            if (list.get(position).getFile_img() != null) {
                holder.ImageView.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);
                Picasso.with(context).load(list.get(position).getFile_img()).placeholder(R.drawable.book).error(R.drawable.book).into(holder.ImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                        holder.ImageView.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onError() {
                        holder.ImageView.setVisibility(View.VISIBLE);
                        holder.progressBar.setVisibility(View.GONE);
                        Picasso.with(context).load(R.drawable.book).placeholder(R.drawable.book).error(R.drawable.book).into(holder.ImageView);
                    }
                });
            } else {
                holder.progressBar.setVisibility(View.GONE);
                holder.ImageView.setVisibility(View.VISIBLE);
                Picasso.with(context).load(R.drawable.book).placeholder(R.drawable.book).error(R.drawable.book).into(holder.ImageView);
            }
            holder.textTitle.setText(list.get(position).getFile_title());
            holder.textSubTitle.setText(list.get(position).getFile_sub_title());
            holder.textAuthor.setText(list.get(position).getAuthor_id());
        } catch (Exception e) { }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
