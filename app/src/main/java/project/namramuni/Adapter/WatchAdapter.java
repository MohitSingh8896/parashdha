package project.namramuni.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.namramuni.Holder.BaseViewHolder;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;

public class WatchAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = WatchAdapter.class.getSimpleName();
    private Context context;
    private List<Gurudevlist> list;
    //position to check which position is selected
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public WatchAdapter(Context context, List<Gurudevlist> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new YoutubeListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_watchs_list, parent, false));
            case VIEW_TYPE_LOADING:
                return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == list.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void add(Gurudevlist response) {
        list.add(response);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<Gurudevlist> postItems) {
        for (Gurudevlist response : postItems) {
            add(response);
        }
    }


    private void remove(Gurudevlist postItems) {
        int position = list.indexOf(postItems);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new Gurudevlist());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = list.size() - 1;
        Gurudevlist item = getItem(position);
        if (item != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    Gurudevlist getItem(int position) {
        return list.get(position);
    }

    public class YoutubeListHolder extends BaseViewHolder {
        public TextView txtTitle, timeDuration;
        public ImageView img;
        public YoutubeListHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            timeDuration = itemView.findViewById(R.id.timeDuration);
        }
        protected void clear() { }
        public void onBind(int position) {
            super.onBind(position);
            Gurudevlist item = list.get(position);
            txtTitle.setText(item.getPlaylist_name());
            timeDuration.setText(item.getTotal() + " Track");
            SetFont.setFont(txtTitle, context, SetFont.bold);
            SetFont.setFont(timeDuration, context, SetFont.normal);
            Picasso.with(context).load(item.getPlaylist_image()).into(img);
        }
    }

    public class FooterHolder extends BaseViewHolder {
        ProgressBar progressBar;

        FooterHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        @Override
        protected void clear() {

        }

    }

}
