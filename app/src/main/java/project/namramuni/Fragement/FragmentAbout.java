package project.namramuni.Fragement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Adapter.AboutAdapter;
import project.namramuni.Pojo.ReadPojo;
import project.namramuni.R;

public class FragmentAbout extends Fragment {
    @BindView(R.id.rcList)
    RecyclerView recyclerView;
    @BindView(R.id.btnTitle)
    Button btnTitle;
    @BindView(R.id.lnr)
    LinearLayout lnr;
    @BindView(R.id.fb)
    ImageButton fb;
    @BindView(R.id.twitter)
    ImageButton twitter;
    @BindView(R.id.in)
    ImageButton in;
    @BindView(R.id.youtub)
    ImageButton utub;
    @BindView(R.id.insta)
    ImageButton insta;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.contactfragemnt, null);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).enableNavigationIcon();
        btnTitle.setText("About Us");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AboutAdapter adapter = new AboutAdapter(getContext(),getData());
        recyclerView.setAdapter(adapter);
        lnr.setVisibility(View.VISIBLE);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/ParasdhamIndia/"));
                startActivity(i);
            }
        });
        utub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.youtube.com/channel/UCzpVSCrRaDXvjT2eH-hJ9eg"));
                startActivity(i);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://twitter.com/hashtag/parasdham?src=hash"));
                startActivity(i);
            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://in.linkedin.com/company/parasdham---india"));
                startActivity(i);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.instagram.com/parasdham"));
                startActivity(i);
            }
        });
        return view;
    }
    private List<ReadPojo> getData() {
        ArrayList<ReadPojo> watchPojos = new ArrayList<>();
        watchPojos.add(new ReadPojo("Namramuni App", "","Version 1.2"));
        watchPojos.add(new ReadPojo("Other Parasdham Apps", "https://play.google.com/store/apps/details?id=skryptech.namramuni","skryptech.namramuni"));
        watchPojos.add(new ReadPojo("Official Parasdham Website", "https://parasdham.org/","parasdham.org"));
        watchPojos.add(new ReadPojo("Parasdham Download Store", "https://parasdham.org/parasdham-app/","parasdham.org/parasdham-app"));
        watchPojos.add(new ReadPojo("Privacy Policy", "https://google.com","https://google.com"));
        return watchPojos;
    }
}
