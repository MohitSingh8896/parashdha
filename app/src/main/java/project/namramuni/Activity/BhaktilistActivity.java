package project.namramuni.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Adapter.AlbumViewAdapter;
import project.namramuni.Adapter.BhaktiSongsLinkAdapter;
import project.namramuni.Adapter.DivinityAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.List.PlayList;
import project.namramuni.ViewModels.Main.PlayListResponse;
import project.namramuni.background.AudioService;
import project.namramuni.background.AudioServiceBinder;
import project.namramuni.background.Connectivity;
import project.namramuni.background.NetworkChangeReceiver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.namramuni.Activity.MainActivity.BloadingPanel;
import static project.namramuni.Activity.MainActivity.Blogo_song;
import static project.namramuni.Activity.MainActivity.Bseek_bar_id;
import static project.namramuni.Activity.MainActivity.Btxt_playing_song_id;
import static project.namramuni.Activity.MainActivity.audioProgressUpdateHandler;
import static project.namramuni.Activity.MainActivity.audioServiceBinder;
import static project.namramuni.Activity.MainActivity.shilpaloadingPanel;
import static project.namramuni.Activity.MainActivity.shilpamini_player_new;
import static project.namramuni.Activity.MainActivity.shilpaplay_song_img;
import static project.namramuni.Activity.MainActivity.shilpasong_playing_name;
import static project.namramuni.Activity.MainActivity.utils;
import static project.namramuni.background.AudioService.shilpaopenPlayer1;
import static project.namramuni.background.AudioService.shilpaplaypouse;
import static project.namramuni.background.AudioService.shilpasetSongUrl;
import static project.namramuni.background.AudioService.shilpasetimag;
import static project.namramuni.background.AudioService.shilpasettitle;
import static project.namramuni.background.AudioService.shilpasongid;
import static project.namramuni.background.AudioServiceBinder.audioPlayer;
import static project.namramuni.background.AudioServiceBinder.dataisActivityVisible;
import static project.namramuni.background.AudioServiceBinder.secondaryProgress;

public class BhaktilistActivity extends AppCompatActivity {
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
    @BindView(R.id.txtGurudevMsg)
    TextView txtGurudevMsg;
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
    static LinearLayout lnrVideo;
    static RelativeLayout lnrImage;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    @BindView(R.id.txtImageName)
    TextView txtImageName;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 5;
    private boolean isLoading = false;
    int itemCount = 0;
    static BhaktiSongsLinkAdapter adapter1;
    List<PlayList> list;
    List<Gurudevlist> videolist;
    AlbumViewAdapter adapter2;
    public static Boolean bounds = false;
    NetworkChangeReceiver mNetworkReceiver;
    long backupPos = 0;
    public static DivinityAdapter divadapter;
    public static RelativeLayout Bbtn_play_pause_id11;
    public static ImageView Bbtn_play_id11;
    public static ImageView Bbtn_pause_id11;
    public static LinearLayout BloadingPanel11;
    public static SeekBar Bseek_bar_id11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activtybhakti);
        ButterKnife.bind(this);
        lnrVideo = findViewById(R.id.lnrVideo);
        lnrImage = findViewById(R.id.lnrImage);
        Bseek_bar_id11 = findViewById(R.id.Bseek_bar_id11);
        BloadingPanel11 = findViewById(R.id.BloadingPanel11);
        Bbtn_pause_id11 = findViewById(R.id.Bbtn_pause_id11);
        Bbtn_play_id11 = findViewById(R.id.Bbtn_play_id11);
        Bbtn_play_pause_id11 = findViewById(R.id.Bbtn_play_pause_id11);
        list = new ArrayList<>();
        lnrVideo.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
        setFont();
        dataisActivityVisible = "sfafs";
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcList.setLayoutManager(linearLayoutManager2);
        rcList.setHasFixedSize(true);
        getListing(getIntent().getStringExtra("id"));
        titlelist.setText(getIntent().getStringExtra("type") + " Playlist");
        Picasso.with(this).load(getIntent().getStringExtra("img")).into(image);
        txtImageName.setText(getIntent().getStringExtra("name"));
        rcList.addOnItemTouchListener(new RecyclerTouchListener(this, rcList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                setOutput(position);
                adapter2.setSelectedPosition(position);
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
        txtVideoName.setOnClickListener(new View.OnClickListener() {
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
        bindAudioService();
//        registerNetworkBroadcastForNougat();
        Bseek_bar_id11.setMax(100);
        Bseek_bar_id11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audioServiceBinder.seekadjust(progress);
                    Bseek_bar_id11.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        Bbtn_play_id11.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (Connectivity.isConnectedFast(BhaktilistActivity.this)) {
                    play("", "");
                } else {
                    Toast.makeText(BhaktilistActivity.this, "Your internet is to slow or no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bbtn_pause_id11.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                pause("");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pause(String type) {
        audioServiceBinder.pauseAudio();
        shilpaplaypouse = false;
//        getPlayPouse1();
    }

    public void registerNetworkBroadcastForNougat() {
        mNetworkReceiver = new NetworkChangeReceiver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    public void bindAudioService() {
        if (audioServiceBinder == null) {
            Intent intent = new Intent(BhaktilistActivity.this, AudioService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unBoundAudioService() {
        if (audioServiceBinder != null) {
            if (bounds == true)
                unbindService(serviceConnection);
        }
    }

    public static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // Cast and assign background service's onBind method returned iBander object.
            audioServiceBinder = (AudioServiceBinder) iBinder;
            bounds = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bounds = false;
        }
    };

    @Override
    protected void onDestroy() {
//        unBoundAudioService();
//        Application.activityPaused();
        dataisActivityVisible = "";
        super.onDestroy();
    }

    public void seekbarUpdater() {
        backupPos = 0;
        if (audioProgressUpdateHandler == null) {
            audioProgressUpdateHandler = new Handler() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void handleMessage(Message msg) {
                    if (audioServiceBinder != null) {
                        try {
                            Bseek_bar_id.setProgress((int) audioServiceBinder.getAudioSeek());
                            Bseek_bar_id11.setProgress((int) audioServiceBinder.getAudioSeek());
                            Bseek_bar_id.setSecondaryProgress(secondaryProgress);
                            Bseek_bar_id11.setSecondaryProgress(secondaryProgress);
                            String totalTime = utils.milliSecondsToTimer(audioServiceBinder.totalAudioDuration());
                            long currentPos = audioServiceBinder.currAudioPosition();
                            if (currentPos == backupPos) {
                                if (audioPlayer != null) {
                                    if (audioPlayer.isPlaying()) {
                                        shilpaloadingPanel.setVisibility(View.VISIBLE);
                                        BloadingPanel.setVisibility(View.VISIBLE);
                                        BloadingPanel11.setVisibility(View.VISIBLE);
                                        Log.d("LoadingAudioSongs", "Load");
                                    } else {
                                        if (currentPos == 0) {
                                        } else {
                                            shilpaloadingPanel.setVisibility(View.GONE);
                                            BloadingPanel.setVisibility(View.GONE);
                                            BloadingPanel11.setVisibility(View.GONE);
                                        }
                                    }
                                } else {
                                    if (currentPos == 0) {
                                    } else {
                                        BloadingPanel11.setVisibility(View.GONE);
                                        shilpaloadingPanel.setVisibility(View.GONE);
                                        BloadingPanel.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                shilpaloadingPanel.setVisibility(View.GONE);
                                BloadingPanel.setVisibility(View.GONE);
                                BloadingPanel11.setVisibility(View.GONE);
                            }
                            backupPos = currentPos;
                            if (msg.what == audioServiceBinder.UPDATE_AUDIO_PROGRESS_BAR) {
                                int currProgress = (int) audioServiceBinder.getAudioProgress();
                                Bseek_bar_id.setProgress(currProgress);
                                Bseek_bar_id11.setProgress(currProgress);
                            }
                        } catch (NullPointerException e) {
                            e.getMessage();
                        }
                    }
                }
            };
        }
    }

    public void setMusic() {
        if (!shilpasetimag.isEmpty()) {
            Picasso.with(BhaktilistActivity.this).load(shilpasetimag).placeholder(R.drawable.logo).into(shilpaplay_song_img, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                    Picasso.with(BhaktilistActivity.this).load(R.drawable.logo).into(shilpaplay_song_img);
                }
            });
            Picasso.with(BhaktilistActivity.this).load(shilpasetimag).into(Blogo_song, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(BhaktilistActivity.this).load(R.drawable.logo).into(Blogo_song);
                }
            });
        }
        Btxt_playing_song_id.setText(shilpasettitle);
        shilpasong_playing_name.setText(shilpasettitle);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void musicView(String count, int point, String image, String song_name, String song_url, String mid) {
        try {
            BloadingPanel11.setVisibility(View.VISIBLE);
            shilpamini_player_new.setVisibility(View.VISIBLE);
            audioProgressUpdateHandler = null;
            audioServiceBinder.stopAudio();
            audioServiceBinder.storekey(mid, song_name, image, song_url, point, count);
            audioServiceBinder.setAudioFileUrl(shilpasetSongUrl);
            setMusic();
            MainActivity.setMusic();
            audioServiceBinder.setStreamAudio(true);
            audioServiceBinder.setContext(this);
            seekbarUpdater();
            MainActivity.seekbarUpdater();
            audioServiceBinder.setAudioProgressUpdateHandler(audioProgressUpdateHandler);
            if (shilpaopenPlayer1 == false) {
                MainActivity.getIntentService(this);
            }
            play("1", "1");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void stopMusic() {
        adapter1.notifyDataSetChanged();
        lnrImage.setVisibility(View.VISIBLE);
        lnrVideo.setVisibility(View.GONE);
        MainActivity.stopMusic();
//        shilpamini_player_new.setVisibility(View.GONE);
//        Bbtn_play_id11.setVisibility(View.GONE);
//        Bbtn_pause_id11.setVisibility(View.VISIBLE);
//        BloadingPanel11.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void play(String type, String s) {
        if (!type.equals("1")) {
            audioServiceBinder.startAudio1("1");
        } else {
            if (s.equals("1")) {
                audioServiceBinder.startAudio1("");
            } else {
                audioServiceBinder.startAudio();
            }
        }
        shilpaplaypouse = true;
    }

    public static void getPlayPouse() {
        if (audioPlayer.isPlaying()) {
            Bbtn_play_id11.setVisibility(View.GONE);
            Bbtn_pause_id11.setVisibility(View.VISIBLE);
        } else {
            Bbtn_play_id11.setVisibility(View.VISIBLE);
            Bbtn_pause_id11.setVisibility(View.GONE);
        }
    }

    public static void getPlay() {
        Bbtn_play_id11.setVisibility(View.GONE);
        Bbtn_pause_id11.setVisibility(View.VISIBLE);
        MainActivity.getPlay();
    }

    public static void getPouse() {
        Bbtn_play_id11.setVisibility(View.VISIBLE);
        Bbtn_pause_id11.setVisibility(View.GONE);
        MainActivity.getPouse();
    }

    private void setFont() {
        SetFont.setFont((TextView) findViewById(R.id.titlelist), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtRelativePost), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtVideoName), this, SetFont.bold);
        SetFont.setFont((TextView) findViewById(R.id.txtVideoDescription), this, SetFont.normal);
    }

    public static String listName = "";

    public String getDate(String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
        try {
            Date fromDate = input.parse(date);
            return output.format(fromDate);
        } catch (Exception e) {
            return date;
        }
    }

    public void getListing(String id) {
        txtRelativePost.setText("Related " + getIntent().getStringExtra("type"));
        listName = "";
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        getAlbumList(id);
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(this, rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view, int position) {
                if (position != -1) {
                    try {
                        shilpasongid = list.get(position).getId();
                        musicView("1", -1, list.get(position).getSong_url(), list.get(position).getSong_name(), list.get(position).getSong_url(), list.get(position).getId());
                        adapter1.notifyDataSetChanged();
                        lnrImage.setVisibility(View.GONE);
                        lnrVideo.setVisibility(View.VISIBLE);
                        txtVideoName.setText(list.get(position).getSong_name());
                        try {
                            txtVideoDescription.setText(getDate(list.get(position).getUpload_time()) + "\n\n" + list.get(position).getSong_name());
                        } catch (Exception e) {
                            txtVideoDescription.setText(list.get(position).getUpload_time() + "\n\n" + list.get(position).getSong_name());
                        }
                        Picasso.with(BhaktilistActivity.this).load(list.get(position).getCover_image()).into(imgBackground);
                    } catch (Exception e) {
                        Toast.makeText(BhaktilistActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view, int position) {
                if (position != -1) {
                    try {
                        shilpasongid = list.get(position).getId();
                        musicView("1", -1, list.get(position).getCover_image(), list.get(position).getTitle(), list.get(position).getSong_url(), list.get(position).getId());
                        adapter1.notifyDataSetChanged();
                        lnrImage.setVisibility(View.GONE);
                        lnrVideo.setVisibility(View.VISIBLE);
                        txtVideoName.setText(list.get(position).getSong_name());
                        try {
                            txtVideoDescription.setText(getDate(list.get(position).getUpload_time()) + "\n\n" + list.get(position).getSong_name());
                        } catch (Exception e) {
                            txtVideoDescription.setText(list.get(position).getUpload_time() + "\n\n" + list.get(position).getSong_name());
                        }
                        Picasso.with(BhaktilistActivity.this).load(list.get(position).getCover_image()).into(imgBackground);
                    } catch (Exception e) {
                        Toast.makeText(BhaktilistActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initYouTubePlayerView(final PlayList videoUrl, Gurudevlist gurudevlist) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE MMM dd, HH:mm a");
        if (gurudevlist == null) {
            txtVideoName.setText(videoUrl.getSong_name());
            try {
                Date fromDate = input.parse(videoUrl.getUpload_time());
                txtVideoDescription.setText(output.format(fromDate) + "\n\n" + videoUrl.getSong_name());
            } catch (Exception e) {
                txtVideoDescription.setText(videoUrl.getUpload_time() + "\n\n" + videoUrl.getSong_name());
            }
            Picasso.with(this).load(videoUrl.getCover_image()).into(imgBackground);
            musicView("1", -1, videoUrl.getCover_image(), videoUrl.getTitle(), videoUrl.getSong_url(), videoUrl.getId());
        } else {
            txtVideoName.setText(gurudevlist.getPlaylist_name());
            try {
                Date fromDate = input.parse(gurudevlist.getCreate_date());
                txtVideoDescription.setText(output.format(fromDate) + "\n\n" + gurudevlist.getPlaylist_name());
            } catch (Exception e) {
                txtVideoDescription.setText(gurudevlist.getCreate_date() + "\n\n" + gurudevlist.getPlaylist_name());
            }
            Picasso.with(this).load(gurudevlist.getPlaylist_image()).into(imgBackground);
            musicView("1", -1, gurudevlist.getPlaylist_image(), gurudevlist.getPlaylist_name(), gurudevlist.getPlaylist_url(), gurudevlist.getId());
        }
    }

    @Override
    public void onBackPressed() {
      /*  if (youTubePlayerMain.isFullScreen())
            youTubePlayerMain.exitFullScreen();
        else*/
        finish();
    }

    private void getAlbumList(String id) {
        Loading.Visibility(this);
        if (NetworkInfo.isNetworkAvailable(this)) {
            Call<PlayListResponse> call = ApiClientMain.getApiClient().getPlayList(id, getIntent().getStringExtra("type").toLowerCase());
            call.enqueue(new Callback<PlayListResponse>() {
                @Override
                public void onResponse(Call<PlayListResponse> call, Response<PlayListResponse> response) {
                    if (response != null && response.body() != null) {
                        PlayListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getData();
                            videolist = userResponse.getPlaylist();
                            txtGurudevMsg.setVisibility(View.GONE);
                            Loading.Visibility(BhaktilistActivity.this, 1);
                        } else {
                            txtGurudevMsg.setVisibility(View.VISIBLE);
                            Loading.Visibility(BhaktilistActivity.this, 2);
                            Toast.makeText(BhaktilistActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (list != null)
                            for (int a = 0; a < list.size(); a++) {
                                if (shilpasongid.equals(list.get(a).getId())) {
                                    lnrImage.setVisibility(View.GONE);
                                    lnrVideo.setVisibility(View.VISIBLE);
                                    getPlayPouse();
                                }
                            }
                        if (videolist != null)
                            for (int a = 0; a < videolist.size(); a++) {
                                if (shilpasongid.equals(videolist.get(a).getId())) {
                                    lnrImage.setVisibility(View.GONE);
                                    lnrVideo.setVisibility(View.VISIBLE);
                                    getPlayPouse();
                                }
                            }
                        adapter1 = new BhaktiSongsLinkAdapter(BhaktilistActivity.this, list, videolist, share, imgBackground,
                                getIntent().getStringExtra("type"), lnrImage, lnrVideo, listName, txtVideoName, txtVideoDescription);
                        rcGurudevList.setAdapter(adapter1);
                    } else {
                        txtGurudevMsg.setVisibility(View.VISIBLE);
                        Toast.makeText(BhaktilistActivity.this, "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(BhaktilistActivity.this, 2);
                    }
                }

                @Override
                public void onFailure(Call<PlayListResponse> call, Throwable t) {
                    txtGurudevMsg.setVisibility(View.VISIBLE);
                    Loading.Visibility(BhaktilistActivity.this, 500);
                }
            });
        } else {
            Loading.Visibility(BhaktilistActivity.this, 404);
        }
    }
}