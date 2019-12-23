package project.namramuni.Fragement;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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

import com.google.android.youtube.player.YouTubePlayerFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.MainActivity;
import project.namramuni.UIDesign.Loading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Adapter.DivinityAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.ViewModels.List.DivinityList;
import project.namramuni.ViewModels.Main.PunyaBankListResponse;

import static project.namramuni.Fragement.FragmentHome.isSongs;

public class FragmentDiviniti extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.lnr3)
    RelativeLayout lnr3;
    @BindView(R.id.lnr2)
    LinearLayout lnr2;
    @BindView(R.id.rlvMeditation)
    RelativeLayout rlvMeditation;
    @BindView(R.id.imgMeditation)
    ImageView imgMeditation;
    @BindView(R.id.txtMeditation)
    TextView txtMeditation;
    @BindView(R.id.rlvMantra)
    RelativeLayout rlvMantra;
    @BindView(R.id.txtMantra)
    TextView txtMantra;
    @BindView(R.id.imgMantra)
    ImageView imgMantra;
    @BindView(R.id.rlvPrathana)
    RelativeLayout rlvPrathana;
    @BindView(R.id.imgPrathana)
    ImageView imgPrathana;
    @BindView(R.id.txtPrathana)
    TextView txtPrathana;
    @BindView(R.id.rlvBhaktiSongs)
    RelativeLayout rlvBhaktiSongs;
    @BindView(R.id.imgBhaktiSongs)
    ImageView imgBhaktiSongs;
    @BindView(R.id.txtBhaktiSongs)
    TextView txtBhaktiSongs;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpunkbank, null);
        ButterKnife.bind(this, view);
        lnr3.setVisibility(View.GONE);
        lnr1.setVisibility(View.GONE);
        lnr2.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        rlvMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("Meditation", view);
            }
        });
        rlvMantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("Mantra", view);
            }
        });
        rlvPrathana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("Prathana", view);
            }
        });
        rlvBhaktiSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab("bhakti", view);
            }
        });
        if (isSongs == true) {
            setTab("bhakti", view);
        } else {
            setTab("Meditation", view);
        }
        isSongs = false;
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTab(String s, View view) {
        setSelection(s);
        getList(s, view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSelection(String s) {
        int punya = R.drawable.punyabank;
        int read2 = R.drawable.read2;
        int white = getContext().getColor(R.color.white);
        int head = getContext().getColor(R.color.heading);
        if (s.equals("Meditation")) {
            rlvMeditation.setBackgroundResource(punya);
            rlvMantra.setBackgroundResource(read2);
            rlvPrathana.setBackgroundResource(read2);
            rlvBhaktiSongs.setBackgroundResource(read2);
            imgMeditation.setColorFilter(white);
            txtMeditation.setTextColor(white);
            imgMantra.setColorFilter(head);
            txtMantra.setTextColor(head);
            imgPrathana.setColorFilter(head);
            txtPrathana.setTextColor(head);
            imgBhaktiSongs.setColorFilter(head);
            txtBhaktiSongs.setTextColor(head);
        } else if (s.equals("Mantra")) {
            rlvMeditation.setBackgroundResource(read2);
            rlvMantra.setBackgroundResource(punya);
            rlvPrathana.setBackgroundResource(read2);
            rlvBhaktiSongs.setBackgroundResource(read2);
            imgMantra.setColorFilter(white);
            txtMantra.setTextColor(white);
            imgMeditation.setColorFilter(head);
            txtMeditation.setTextColor(head);
            imgPrathana.setColorFilter(head);
            txtPrathana.setTextColor(head);
            imgBhaktiSongs.setColorFilter(head);
            txtBhaktiSongs.setTextColor(head);
        } else if (s.equals("Prathana")) {
            rlvMeditation.setBackgroundResource(read2);
            rlvMantra.setBackgroundResource(read2);
            rlvPrathana.setBackgroundResource(punya);
            rlvBhaktiSongs.setBackgroundResource(read2);
            imgPrathana.setColorFilter(white);
            txtPrathana.setTextColor(white);
            imgMeditation.setColorFilter(head);
            txtMeditation.setTextColor(head);
            imgMantra.setColorFilter(head);
            txtMantra.setTextColor(head);
            imgBhaktiSongs.setColorFilter(head);
            txtBhaktiSongs.setTextColor(head);
        } else if (s.equals("bhakti")) {
            rlvMeditation.setBackgroundResource(read2);
            rlvMantra.setBackgroundResource(read2);
            rlvPrathana.setBackgroundResource(read2);
            rlvBhaktiSongs.setBackgroundResource(punya);
            imgBhaktiSongs.setColorFilter(white);
            txtBhaktiSongs.setTextColor(white);
            imgMeditation.setColorFilter(head);
            txtMeditation.setTextColor(head);
            imgMantra.setColorFilter(head);
            txtMantra.setTextColor(head);
            imgPrathana.setColorFilter(head);
            txtPrathana.setTextColor(head);
        }
    }

    List<DivinityList> filelist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getList(String type, View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<PunyaBankListResponse> call = ApiClientMain.getApiClient().getDivinity(type);
            call.enqueue(new Callback<PunyaBankListResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<PunyaBankListResponse> call, Response<PunyaBankListResponse> response) {
                    if (response != null && response.body() != null) {
                        PunyaBankListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            filelist = userResponse.getPlay_list();
                            if (filelist != null) {
                                setList(filelist, type);
                            } else {
                                filelist = new ArrayList<>();
                                setList(filelist, type);
                            }
                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            filelist = new ArrayList<>();
                            setList(filelist, type);
                            Loading.Visibility(getContext(), view, 1);
                        }
                    } else {
                        filelist = new ArrayList<>();
                        setList(filelist, type);
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<PunyaBankListResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                    filelist = new ArrayList<>();
                    setList(filelist, type);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
            filelist = new ArrayList<>();
            setList(filelist, type);
        }
    }

    private void setList(List<DivinityList> filter, String type) {
        if (filter.size() == 0) {

        } else {
            MainActivity.divadapter = new DivinityAdapter(getActivity(), filter, type);
            rcGurudevList.setAdapter(MainActivity.divadapter);
        }
    }
}
