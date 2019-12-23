package project.namramuni.Fragement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.Adapter.WatchAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.Main.GurudevVideolistResponse;

public class FragmentListen extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.btntitle)
    Button title;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    LinearLayoutManager linearLayoutManager1;
    WatchAdapter adapter1;
    List<Gurudevlist> videolist = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentgurudev, null);
        ButterKnife.bind(this, view);
        lnr1.setVisibility(View.GONE);
        linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        getList(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                getContext().startActivity(new Intent(getContext(), BhaktilistActivity.class)
                        .putExtra("id", videolist.get(position).getId())
                        .putExtra("img", videolist.get(position).getPlaylist_image())
                        .putExtra("name", videolist.get(position).getPlaylist_name())
                        .putExtra("type", "Audio")
                );
//                ((MainActivity) getActivity()).showFragment3(new PlaylistActivity(), "set","1");
            }
        }));
        return view;
    }
    private void getList(View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<GurudevVideolistResponse> call = ApiClientMain.getApiClient().getAudio();
            call.enqueue(new Callback<GurudevVideolistResponse>() {
                @Override
                public void onResponse(Call<GurudevVideolistResponse> call, Response<GurudevVideolistResponse> response) {
                    if (response != null && response.body() != null) {
                        GurudevVideolistResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            videolist = userResponse.getData();
                            Collections.reverse(videolist);
                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        adapter1 = new WatchAdapter(getContext(), videolist);
                        rcGurudevList.setAdapter(adapter1);
                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<GurudevVideolistResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }
}
