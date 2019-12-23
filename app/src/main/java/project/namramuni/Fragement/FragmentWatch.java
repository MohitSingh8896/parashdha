package project.namramuni.Fragement;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.PlaylistActivity;
import project.namramuni.Adapter.WatchAdapter1;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.Gurudevlist;

public class FragmentWatch extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.btntitle)
    Button title;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    LinearLayoutManager linearLayoutManager1;
    WatchAdapter1 adapter11;
    List<RecentVideoList> videolist1 = new ArrayList<>();
    String key="3" ,tkey="2";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentgurudev, null);
        ButterKnife.bind(this, view);
        lnr1.setVisibility(View.GONE);
        linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);

        getliveVideo(key, tkey, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


//                getContext().startActivity(new Intent(getContext(), PlaylistActivity.class)
//
//                        .putExtra("id", videolist1.get(position).getId())
//
//                        .putExtra("name", videolist.get(position).getPlaylist_name())
//                        .putExtra("img", videolist.get(position).getPlaylist_image())
//                        .putExtra("type", "video")
//                );

             //   Toast.makeText(getActivity(), ""+videolist1.get(position).getId(), Toast.LENGTH_SHORT).show();

            }
        }));
        return view;
    }










    private void getliveVideo(String key,String tkey,View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<NewVideoResponse> call = ApiClientMain.getApiClient().getGurudevPlaylist(key, tkey);
            call.enqueue(new Callback<NewVideoResponse>() {
                @Override
                public void onResponse(Call<NewVideoResponse> call, Response<NewVideoResponse> response) {
                    if (response != null && response.body() != null) {
                        NewVideoResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {

                            videolist1 = userResponse.getRecord();

                            Loading.Visibility(getContext(), view, 1);

                        } else {
                            Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if(videolist1!=null){
                            adapter11 = new WatchAdapter1(getContext(), videolist1);
                                          rcGurudevList.setAdapter(adapter11);
                                         Collections.reverse(videolist1);
                        }
                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view, 2);
                    }
                }
                @Override
                public void onFailure(Call<NewVideoResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }


}
