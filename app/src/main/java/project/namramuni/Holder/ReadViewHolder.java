package project.namramuni.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class ReadViewHolder extends RecyclerView.ViewHolder {
    public ImageView ImageView;
    public ProgressBar progressBar;
    public TextView textTitle,textSubTitle,textAuthor;
    public Button shibir_joinbutton;

    public RelativeLayout reyclick;
    public ReadViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.textTitle);
        textSubTitle = itemView.findViewById(R.id.textSubTitle);
        textAuthor = itemView.findViewById(R.id.textAuthor);
        ImageView = itemView.findViewById(R.id.imgBook);
        progressBar = itemView.findViewById(R.id.progressBar);
        shibir_joinbutton = itemView.findViewById(R.id.shibir_joinbutton);
        reyclick = itemView.findViewById(R.id.reyclick);

    }
}
