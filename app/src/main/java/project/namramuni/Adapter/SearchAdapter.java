package project.namramuni.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.namramuni.Holder.ScoreHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.List.Scorelist;

public class SearchAdapter extends RecyclerView.Adapter<ScoreHolder> {
    Context context;
    private List<RecentVideoList> list;

    public SearchAdapter(Context context, List<RecentVideoList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_search, parent, false);
        return new ScoreHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
        try {
            String upperString = list.get(position).getTitle().substring(0,1).toUpperCase() + list.get(position).getTitle().substring(1);
                holder.textTitle.setText(Html.fromHtml(upperString));
              //  holder.txtdes.setText("Description :- "+list.get(position).getDescription());








        } catch (Exception e) { }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
