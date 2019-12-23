package project.namramuni.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.Holder.ContactViewHolder;
import project.namramuni.R;
import project.namramuni.ViewModels.List.ContactList;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    Context context;
    List<ContactList> list;
    public ContactAdapter(Context context, List<ContactList> imageId1) {
        this.context = context;
        this.list = imageId1;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        try {
            holder.titleHeading.setText(list.get(position).getCenter_name());
            if (!list.get(position).getCenter_phone_no().equals("")) {
                holder.txtPhone.setText("Phone: "+list.get(position).getCenter_phone_no());
            } else {
                holder.lnrPhone.setVisibility(View.GONE);
            }
            if (!list.get(position).getCenter_address().equals("")) {
                holder.txtsubTitle.setText(list.get(position).getCenter_address());
            } else {
                holder.lnrAddress.setVisibility(View.GONE);
            }
            holder.txtPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+list.get(position).getCenter_phone_no()));
                    context.startActivity(intent);
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
