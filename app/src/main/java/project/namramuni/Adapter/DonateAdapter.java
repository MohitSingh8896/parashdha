package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.DonateViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Donatelist;

public class DonateAdapter extends RecyclerView.Adapter<DonateViewHolder> {
    Context context;
    List<Donatelist> list;
    int isSelection = -1;
    LayoutInflater inflter;
    RecyclerView gridView;
    int nPrevSelGridItem = -1;

    public DonateAdapter(Context context, List<Donatelist> imageId1, RecyclerView rcListDonate) {
        this.context = context;
        this.list = imageId1;
        gridView = rcListDonate;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public DonateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_donate_list, parent, false);
        return new DonateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonateViewHolder holder, int position) {
        try {
            SetFont.setFont(holder.textTitle, context, SetFont.bold);
            Picasso.with(context).load(list.get(position).getDonate_image()).into(holder.ImageView);
            holder.textTitle.setText(list.get(position).getDonate_title());
            holder.rlvDonat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (nPrevSelGridItem != -1) {
                            View viewPrev = (View) gridView.getChildAt(nPrevSelGridItem);
                            ImageView selection = viewPrev.findViewById(R.id.isSelection);
                            selection.setVisibility(View.GONE);
                        }
                        nPrevSelGridItem = position;
                        if (nPrevSelGridItem == position) {
                            holder.isSelection.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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




/*
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = inflter.inflate(R.layout.view_donate_list, null); // inflate the layout
        TextView textTitle = itemView.findViewById(R.id.textTitle);
        ImageView selection = itemView.findViewById(R.id.isSelection);
        RelativeLayout rlvDonat = itemView.findViewById(R.id.rlvDonat);
        ImageView imageView = itemView.findViewById(R.id.imgBook);
        try {
            selection.setVisibility(View.GONE);
            Picasso.with(context).load(list.get(position).getDonate_image()).into(imageView);
            textTitle.setText(list.get(position).getDonate_title());
            rlvDonat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (nPrevSelGridItem != -1) {
                            View viewPrev = (View) gridView.getChildAt(nPrevSelGridItem);
                            ImageView selection = viewPrev.findViewById(R.id.isSelection);
                            selection.setVisibility(View.GONE);
                        }
                        nPrevSelGridItem = position;
                        if (nPrevSelGridItem == position) {
                            selection.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
        }
        return itemView;
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }*/

