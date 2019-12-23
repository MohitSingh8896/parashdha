package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.AboutViewHolder;
import project.namramuni.Pojo.ReadPojo;
import project.namramuni.R;

public class AboutAdapter extends RecyclerView.Adapter<AboutViewHolder> {
    Context context;
    List<ReadPojo> list;
    public AboutAdapter(Context context, List<ReadPojo> imageId1) {
        this.context = context;
        this.list = imageId1;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.about, parent, false);
        return new AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutViewHolder holder, int position) {
        try {
            holder.titleHeading.setText(list.get(position).getTitle());
            if (position==0){
                try {
                    PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                    String version = pInfo.versionName;
                    holder.txtPhone.setText("Version "+version);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                holder.img1.setVisibility(View.GONE);
            } else {
                holder.txtPhone.setText(list.get(position).getUthor());
                holder.img1.setVisibility(View.VISIBLE);
            }
            holder.rlv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!list.get(position).getSubtitle().equals("")){
                        Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(list.get(position).getSubtitle()));
                        context.startActivity(i);
                    } else { }
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
