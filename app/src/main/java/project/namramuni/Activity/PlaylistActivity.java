package project.namramuni.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Adapter.YoutubeVideoLinkAdapter1;
import project.namramuni.Listener.MediaPlayers;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Adapter.AlbumViewAdapter;

import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;

import static com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION;


public class PlaylistActivity extends YouTubeBaseActivity {
    @BindView(R.id.toolbar1)
    Toolbar toolbar1;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.txtVideoName)
    TextView txtVideoName;
    @BindView(R.id.titlelist)
    TextView titlelist;
    @BindView(R.id.txtRelativePost)
    TextView txtRelativePost;
    @BindView(R.id.txtVideoDescription)
    TextView txtVideoDescription;
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.rcList)
    MultiSnapRecyclerView rcList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.lnrVideo)
    LinearLayout lnrVideo;
    @BindView(R.id.lnrImage)
    RelativeLayout lnrImage;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txtImageName)
    TextView txtImageName;
    private YouTubePlayerView youTubePlayerView;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 5;
    private boolean isLoading = false;
    int itemCount = 0;
    //YoutubeVideoLinkAdapter adapter1;
    YoutubeVideoLinkAdapter1 adapter1;
   // List<PlayList> list;
    List<RecentVideoList> list;

    AlbumViewAdapter adapter2;
    YouTubePlayer youTubePlayerMain;
    String key="2";
    String tkey = "3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragmentvideo);
        ButterKnife.bind(this);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        list = new ArrayList<>();
        lnrVideo.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
        setFont();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcList.setLayoutManager(linearLayoutManager2);
        rcList.setHasFixedSize(true);

        getListing(getIntent().getStringExtra("id"));
        titlelist.setText(getIntent().getStringExtra("type")+" Playlist" );
        Picasso.with(this).load(getIntent().getStringExtra("img")).into(image);
        txtImageName.setText(getIntent().getStringExtra("name"));

        rcList.addOnItemTouchListener(new RecyclerTouchListener(this, rcList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                setOutput(position);
                adapter1.setSelectedPosition(position);
            }
        }));
        arrow.animate().rotation(270).start();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtVideoDescription.getVisibility() == View.GONE) {
                    NetworkInfo.expand(txtVideoDescription);
                    arrow.animate().rotation(90).start();
                } else {
                    NetworkInfo.collapse(txtVideoDescription);
                    arrow.animate().rotation(270).start();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void setFont() {
        SetFont.setFont((TextView) findViewById(R.id.titlelist), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtRelativePost), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtVideoName), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtVideoDescription), this, SetFont.normal);
    }

    public static String listName = "";

    public void getListing(String id) {
       // txtRelativePost.setText("Related "+getIntent().getStringExtra("type"));
        listName = "";
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        getAlbumList(key,tkey, id);
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(this, rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position != -1) {
                    lnrImage.setVisibility(View.GONE);
                    lnrVideo.setVisibility(View.VISIBLE);
                    adapter1.positionSet = String.valueOf(position);
                    listName = String.valueOf(position);
                    initYouTubePlayerView(list.get(position));
                    adapter1.notifyDataSetChanged();
                    share.setVisibility(View.VISIBLE);
                }
            }
        }));
    }


    public void setOutput(int position) {
        if (list != null)
            list.clear();
        listName = "";
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(this, rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position != -1) {
                    lnrImage.setVisibility(View.GONE);
                    lnrVideo.setVisibility(View.VISIBLE);
                    listName = String.valueOf(position);
                    adapter1.positionSet = String.valueOf(position);
                    initYouTubePlayerView(list.get(position));
                    adapter1.notifyDataSetChanged();
                    share.setVisibility(View.VISIBLE);
                }
            }
        }));
    }
    public String getDate(String date){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
        try{
            Date fromDate = input.parse(date);
            return output.format(fromDate);
        }catch (Exception e){
            return date;
        }
    }

    private void initYouTubePlayerView(final RecentVideoList videoUrl) {
        txtVideoName.setText(videoUrl.getTitle());
        txtVideoDescription.setText(getDate(videoUrl.getPublishedAt())+"\n\n"+videoUrl.getDescription());
        MediaPlayers.getMusicStop();
        if (youTubePlayerMain != null) {
            try {
                youTubePlayerMain.pause();
                youTubePlayerMain.cueVideo(videoUrl.getVideoId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            youTubePlayerView.initialize("AIzaSyCWLpEr7TqEFBKYzk9npcS9rDX8hqSfe2I", new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    // do any work here to cue video, play video, etc.
                    youTubePlayerMain = youTubePlayer;
                    if (!b) {
                        try {
                            youTubePlayer.cueVideo(videoUrl.getVideoId());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Somrthing Went Wrong with this video ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    youTubePlayerMain.setFullscreenControlFlags(FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                }
                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    if (youTubeInitializationResult.isUserRecoverableError()) {
                        youTubeInitializationResult.getErrorDialog(PlaylistActivity.this, 1).show();
                    } else {

                        Toast.makeText(PlaylistActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
      /*  if (youTubePlayerMain.isFullScreen())
            youTubePlayerMain.exitFullScreen();
        else*/
        finish();
    }









    private void getAlbumList(String key, String tkey,String playlist_id) {
        Loading.Visibility(this);
        if (NetworkInfo.isNetworkAvailable(this)) {
            Call<NewVideoResponse> call = ApiClientMain.getApiClient().getvideollist(key,tkey,playlist_id);

            call.enqueue(new Callback<NewVideoResponse>() {
                @Override
                public void onResponse(Call<NewVideoResponse> call, Response<NewVideoResponse> response) {
                    if (response != null && response.body() != null) {
                        NewVideoResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getRecord();
                            Loading.Visibility(PlaylistActivity.this, 1);
                        } else {
                            Loading.Visibility(PlaylistActivity.this, 2);
                            Toast.makeText(PlaylistActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                         adapter1 = new YoutubeVideoLinkAdapter1 (PlaylistActivity.this, list);
                        rcGurudevList.setAdapter(adapter1);
                    } else {
                        Toast.makeText(PlaylistActivity.this, "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(PlaylistActivity.this, 2);
                    }
                }

                @Override
                public void onFailure(Call<NewVideoResponse> call, Throwable t) {
                    Loading.Visibility(PlaylistActivity.this, 500);
                }
            });
        } else {
            Loading.Visibility(PlaylistActivity.this, 404);
        }
    }



//    private void getAlbumList(String id) {
//        Loading.Visibility(this);
//        if (NetworkInfo.isNetworkAvailable(this)) {
//            Call<PlayListResponse> call = ApiClientMain.getApiClient().getPlayList(id,
//                    getIntent().getStringExtra("type").toLowerCase());
//            call.enqueue(new Callback<PlayListResponse>() {
//                @Override
//                public void onResponse(Call<PlayListResponse> call, Response<PlayListResponse> response) {
//                    if (response != null && response.body() != null) {
//                        PlayListResponse userResponse = response.body();
//                        if (userResponse.getStatus().equals("success")) {
//                            list = userResponse.getData();
//                            Loading.Visibility(PlaylistActivity.this, 1);
//                        } else {
//                            Loading.Visibility(PlaylistActivity.this, 2);
//                            Toast.makeText(PlaylistActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                        adapter1 = new YoutubeVideoLinkAdapter(PlaylistActivity.this, list, share);
//                        rcGurudevList.setAdapter(adapter1);
//                    } else {
//                        Toast.makeText(PlaylistActivity.this, "Response Error", Toast.LENGTH_LONG).show();
//                        Loading.Visibility(PlaylistActivity.this, 2);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PlayListResponse> call, Throwable t) {
//                    Loading.Visibility(PlaylistActivity.this, 500);
//                }
//            });
//        } else {
//            Loading.Visibility(PlaylistActivity.this, 404);
//        }
//    }
}