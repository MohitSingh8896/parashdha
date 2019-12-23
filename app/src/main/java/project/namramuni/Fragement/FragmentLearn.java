package project.namramuni.Fragement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.PDFViewerActivity;
import project.namramuni.Adapter.LearnAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.LearnList;
import project.namramuni.ViewModels.Main.LearnResponse;

import static project.namramuni.Application.masterContext;

public class FragmentLearn extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.lnr2)
    LinearLayout lnr2;
    @BindView(R.id.lnr3)
    RelativeLayout lnr3;
    @BindView(R.id.imgclose)
    ImageView imgclose;
    @BindView(R.id.rlvSamayik)
    RelativeLayout rlvSamayik;
    @BindView(R.id.imgSamayik)
    ImageView imgSamayik;
    @BindView(R.id.txtSamayik)
    TextView txtSamayik;
    @BindView(R.id.rlvPratikraman)
    RelativeLayout rlvPratikraman;
    @BindView(R.id.txtPratikraman)
    TextView txtPratikraman;
    @BindView(R.id.imgPratikraman)
    ImageView imgPratikraman;
    PDFView pdfView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpunkbank, null);
        ButterKnife.bind(this, view);
        lnr1.setVisibility(View.VISIBLE);
        lnr2.setVisibility(View.GONE);
        lnr3.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        setSelection(view, "samyik");
        rlvSamayik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(view, "samyik");
            }
        });
        rlvPratikraman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(view, "pratikraman");
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr3.setVisibility(View.GONE);
            }
        });
        pdfView = view.findViewById(R.id.pdfView);
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                displayFromAsset(filelist.get(position).getFile_url());
            }
        }));
        return view;
    }

    private void displayFromAsset(String assetFileName) {
        startActivity(new Intent(getContext(), PDFViewerActivity.class)
                .putExtra("pdf", assetFileName)
        );
    }

    List<LearnList> filelist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getList(String id, View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<LearnResponse> call = ApiClientMain.getApiClient().getLearn(id);
            call.enqueue(new Callback<LearnResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<LearnResponse> call, Response<LearnResponse> response) {
                    if (response != null && response.body() != null) {
                        LearnResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            filelist = userResponse.getPlay_list();
                            if (filelist != null) {
                                setList(filelist);
                            } else {
                                filelist = new ArrayList<>();
                                setList(filelist);
                            }
                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            Loading.Visibility(getContext(), view, 1);
                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<LearnResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSelection(View view, String type) {
        if (type.equals("samyik")) {
            rlvSamayik.setBackgroundResource(R.drawable.punyabank);
            rlvPratikraman.setBackgroundResource(R.drawable.read2);
            imgSamayik.setColorFilter(getContext().getColor(R.color.white));
            imgPratikraman.setColorFilter(getContext().getColor(R.color.heading));
            txtSamayik.setTextColor(getContext().getColor(R.color.white));
            txtPratikraman.setTextColor(getContext().getColor(R.color.heading));
        } else {
            rlvSamayik.setBackgroundResource(R.drawable.read2);
            rlvPratikraman.setBackgroundResource(R.drawable.punyabank);
            imgSamayik.setColorFilter(getContext().getColor(R.color.heading));
            imgPratikraman.setColorFilter(getContext().getColor(R.color.white));
            txtSamayik.setTextColor(getContext().getColor(R.color.heading));
            txtPratikraman.setTextColor(getContext().getColor(R.color.white));
        }
        getList(type, view);
    }

    LearnAdapter adapter;

    private void setList(List<LearnList> filter) {
        adapter = new LearnAdapter(getContext(), filter);
        rcGurudevList.setAdapter(adapter);
    }
}
