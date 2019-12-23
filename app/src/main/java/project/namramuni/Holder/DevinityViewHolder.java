package project.namramuni.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.droidsonroids.gif.GifImageView;
import project.namramuni.R;

public class DevinityViewHolder extends RecyclerView.ViewHolder {
    public ImageView ImageView,icon;
    public ProgressBar progressBar;
    public TextView textTitle,textSubTitle;
    public Button btnMeditation;
    public TextView textCount;
    public GifImageView audioPlay;
    public RelativeLayout clickSongs;
    public DevinityViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        clickSongs = itemView.findViewById(R.id.clickSongs);
        textCount = itemView.findViewById(R.id.textCount);
        audioPlay = itemView.findViewById(R.id.audioPlay);
        progressBar = itemView.findViewById(R.id.progressBar);
        textTitle = itemView.findViewById(R.id.textTitle);
        textSubTitle = itemView.findViewById(R.id.textSubTitle);
        btnMeditation = itemView.findViewById(R.id.btnMeditation);
        ImageView = itemView.findViewById(R.id.imgBook);
    }
}
