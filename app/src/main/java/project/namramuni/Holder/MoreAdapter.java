package project.namramuni.Holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Pojo.MenuDataModel;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;

/**
 * Created by admin on 7/26/2018.
 */

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MyViewHolder> {

    private ArrayList<MenuDataModel> dataSet;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewIcon;
        Switch switcher;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.txt_menu_id);
            this.switcher = (Switch) itemView.findViewById(R.id.switcher);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.img_menu_id);
        }
    }
    Context context;
    public MoreAdapter(Context context, ArrayList<MenuDataModel> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_more_detail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        ImageView imageView = holder.imageViewIcon;
        textViewName.setText(dataSet.get(listPosition).getName());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
        SetFont.setFont(textViewName,context,SetFont.bold);
        if (listPosition==2){
            holder.switcher.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
