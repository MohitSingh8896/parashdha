package project.namramuni.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.namramuni.R;

public class ScoreHolder extends RecyclerView.ViewHolder {
    public TextView textTitle,txtScore,txtrank;
    public  View viewline;
    public ScoreHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.txtTitle);
        txtScore = itemView.findViewById(R.id.txtScore);
        viewline = itemView.findViewById(R.id.viewline);
        txtrank = itemView.findViewById(R.id.txtrank);

//        txtdes = itemView.findViewById(R.id.txtdes);
    }
}
