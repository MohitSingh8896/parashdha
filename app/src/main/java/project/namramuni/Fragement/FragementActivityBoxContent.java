package project.namramuni.Fragement;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Adapter.GranthAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.MediaPlayers;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.Scorelist;
import project.namramuni.ViewModels.Main.ActivityBoxContent;
import project.namramuni.ViewModels.Main.HomeListResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.namramuni.Activity.MainActivity.audioPlayer;

public class FragementActivityBoxContent extends Fragment {
    @BindView(R.id.txtCompleteMsg)
    TextView txtCompleteMsg;
    @BindView(R.id.activityTitle)
    TextView activityTitle;
    @BindView(R.id.textHeading)
    TextView textHeading;
    @BindView(R.id.subrlv1)
    RelativeLayout subrlv1;
    @BindView(R.id.subrlv2)
    RelativeLayout subrlv2;
    @BindView(R.id.imgclose1)
    ImageView imgclose1;
    @BindView(R.id.imgAudio)
    ImageView imgAudio;
    @BindView(R.id.audioPlay)
    GifImageView audioPlay;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioComplete)
    RadioButton radioComplete;
    @BindView(R.id.radioNotComplete)
    RadioButton radioNotComplete;
    @BindView(R.id.rcIndividualList)
    RecyclerView rcIndividualList;
    @BindView(R.id.rcGroupList)
    RecyclerView rcGroupList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    PreferenceManager preferenceManager;
    List<Scorelist> group_score = new ArrayList<>();
    List<Scorelist> individual_score = new ArrayList<>();


     String key="5";
     String tkey = "5";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activitycontent, null);
        ButterKnife.bind(this, view);
        String id = getArguments().getString("id");
        preferenceManager = new PreferenceManager(getContext(), "");

        if (id.equals("5")) {
            subrlv2.setVisibility(View.VISIBLE);
            subrlv1.setVisibility(View.GONE);

            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
            rcIndividualList.setLayoutManager(linearLayoutManager1);
            rcIndividualList.setHasFixedSize(true);

            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
            rcGroupList.setLayoutManager(linearLayoutManager2);
            rcGroupList.setHasFixedSize(true);

            getAllList(key,tkey,view);

        } else {
            getList(view, id);
        }
        imgclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAudio.setVisibility(View.VISIBLE);
                audioPlay.setVisibility(View.GONE);
                ((MainActivity) getActivity()).backstackFragment();
                MediaPlayers.getMusicStop();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                getActivityCompleted(subid, rb.getText().toString());
            }
        });
        audioPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MediaPlayers.getMusicPre(uri);
                    MediaPlayers.getMusicStop();
                    audioPlay.setVisibility(View.GONE);
                    imgAudio.setVisibility(View.VISIBLE);
                }catch (Exception e){

                }
            }
        });
        imgAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAudio.setVisibility(View.GONE);
                audioPlay.setVisibility(View.VISIBLE);
                audioPlayer.start();
            }
        });
        radioComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioComplete.setChecked(true);
                radioNotComplete.setChecked(false);
            }
        });
        radioNotComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioNotComplete.setChecked(true);
                radioComplete.setChecked(false);
            }
        });
        return view;
    }


    private void getAllList(String key,String tkey,View view) {
       // Loading.Visibility(view);
        progressBar.setVisibility(View.VISIBLE);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<HomeListResponse> call = ApiClientMain.getApiClient().getScore(key,tkey,id);
            call.enqueue(new Callback<HomeListResponse>() {
                @Override
                public void onResponse(Call<HomeListResponse> call, Response<HomeListResponse> response) {
                    if (response != null && response.body() != null) {
                        HomeListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            group_score = userResponse.getGroup_score();
                            individual_score = userResponse.getIndividual_score();
                          //  Loading.Visibility(getContext(), view, 1);
                            progressBar.setVisibility(View.INVISIBLE);

                        } else {
                          //  Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                          getScore(group_score);
                          getIndivisual(individual_score);

                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                      //  Loading.Visibility(getContext(), view, 2);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<HomeListResponse> call, Throwable t) {
                  //  Loading.Visibility(getContext(), view, 500);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }



    private void getScore(List<Scorelist> list) {
        GranthAdapter adapter2 = new GranthAdapter(getContext(), (ArrayList<Scorelist>) list);
        rcGroupList.setAdapter(adapter2);

    }

    private void getIndivisual(List<Scorelist> list) {
        GranthAdapter adapter2 = new GranthAdapter(getContext(), (ArrayList<Scorelist>) list);
        rcIndividualList.setAdapter(adapter2);

    }




    String uri = "https://api.soundcloud.com/tracks/678978786/stream/?client_id=6aSX01kZxpetA85mf5R9Ezqs3ozjO2zc";

    String subid="";
    private void getList(View view, String id) {
        progressBar.setVisibility(View.VISIBLE);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            preferenceManager = new PreferenceManager(getContext(), "");
            String userid = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<ActivityBoxContent> call = ApiClientMain.getApiClient().getActivityList(userid, id);
            call.enqueue(new Callback<ActivityBoxContent>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<ActivityBoxContent> call, Response<ActivityBoxContent> response) {
                    if (response != null && response.body() != null) {
                        ActivityBoxContent userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                subid = userResponse.getId();
                                activityTitle.setText(userResponse.getHeading_title());
                                textHeading.setText(Html.fromHtml(userResponse.getLyrics_text()));
                                subrlv1.setVisibility(View.VISIBLE);
                                txtCompleteMsg.setVisibility(View.GONE);
                                subrlv2.setVisibility(View.GONE);
//                                MediaPlayers.getMusicPre(uri);
                                if (userResponse.getIs_complete().equals("N")) {

                                } else {
                                    radioGroup.setVisibility(View.GONE);
                                    txtCompleteMsg.setVisibility(View.VISIBLE);
                                    btnSave.setVisibility(View.GONE);
                                    Toast.makeText(getContext(), "Activity is Completed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                subrlv2.setVisibility(View.GONE);
                                subrlv1.setVisibility(View.GONE);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
//                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            subrlv2.setVisibility(View.GONE);
                            subrlv1.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
//                            Loading.Visibility(getContext(), view, 2);
                        }
                    } else {
                        subrlv2.setVisibility(View.GONE);
                        subrlv1.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Response error", Toast.LENGTH_SHORT).show();
//                        Loading.Visibility(getContext(), view, 2);
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ActivityBoxContent> call, Throwable t) {
//                    Loading.Visibility(getContext(), view, 500);
                    progressBar.setVisibility(View.GONE);
                    subrlv2.setVisibility(View.GONE);
                    subrlv1.setVisibility(View.GONE);
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
//            Loading.Visibility(getContext(), view, 404);
            subrlv2.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            subrlv1.setVisibility(View.GONE);
            Toast.makeText(getContext(), "No internet connetion", Toast.LENGTH_SHORT).show();

        }
    }

    private void getActivityCompleted(String id, String status) {
        progressBar.setVisibility(View.VISIBLE);
        subrlv2.setVisibility(View.GONE);
        subrlv1.setVisibility(View.GONE);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            preferenceManager = new PreferenceManager(getContext(), "");
            String completestatus = "Y";
            String userid = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            if (status.equals("Completed"))
                completestatus = "Y";
            else
                completestatus = "N";
            Call<ActivityBoxContent> call = ApiClientMain.getApiClient().getActivityComplete(userid, id, completestatus);
            call.enqueue(new Callback<ActivityBoxContent>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<ActivityBoxContent> call, Response<ActivityBoxContent> response) {
                    if (response != null && response.body() != null) {
                        ActivityBoxContent userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            MediaPlayers.getMusicStop();
                            imgAudio.setVisibility(View.VISIBLE);
                            audioPlay.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).backstackFragment();
                            Toast.makeText(getContext(), "Activity is Completed", Toast.LENGTH_SHORT).show();
//                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            subrlv2.setVisibility(View.GONE);
                            subrlv1.setVisibility(View.GONE);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Loading.Visibility(getContext(), view, 2);
                        }
                    } else {
                        subrlv2.setVisibility(View.GONE);
                        subrlv1.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Response error", Toast.LENGTH_SHORT).show();
//                        Loading.Visibility(getContext(), view, 2);
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ActivityBoxContent> call, Throwable t) {
//                    Loading.Visibility(getContext(), view, 500);
                    progressBar.setVisibility(View.GONE);
                    subrlv2.setVisibility(View.GONE);
                    subrlv1.setVisibility(View.GONE);
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
//            Loading.Visibility(getContext(), view, 404);
            subrlv2.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            subrlv1.setVisibility(View.GONE);
            Toast.makeText(getContext(), "No internet connetion", Toast.LENGTH_SHORT).show();

        }
    }

}
