package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.ScoreHolder;
import project.namramuni.Pojo.ReadPojo;
import project.namramuni.R;
import project.namramuni.ViewModels.List.Scorelist;

public class GranthAdapter extends RecyclerView.Adapter<ScoreHolder> {
    Context context;
    ArrayList<Scorelist> list;
    public GranthAdapter(Context context, ArrayList<Scorelist> imageId1) {
        this.context = context;
        this.list = imageId1;
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_score, parent, false);
        return new ScoreHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
        try {

            String upperString = list.get(position).getName().substring(0,1).toUpperCase() + list.get(position).getName().substring(1);

            if(list.get(position).getTotal_points().equals("0")){
                holder.viewline.setVisibility(View.GONE);
                holder.textTitle.setVisibility(View.GONE);
                holder.txtScore.setVisibility(View.GONE);
                holder.txtrank.setVisibility(View.GONE);


            }else {

                holder.textTitle.setText(upperString);
                holder.txtScore.setText(list.get(position).getTotal_points());
                holder.txtrank.setText(list.get(position).getRank());
            }






        } catch (Exception e) { }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
