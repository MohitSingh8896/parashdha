package project.namramuni.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;
import com.stfalcon.bottomtablayout.BottomTabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.namramuni.Adapter.DivinityAdapter;
import project.namramuni.Adapter.SongsAdapter;
import project.namramuni.Application;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Fragement.FragmentDonat;
import project.namramuni.Fragement.FragmentGallery;
import project.namramuni.Fragement.FragmentGurudev;
import project.namramuni.Fragement.FragmentHome;
import project.namramuni.Fragement.FragmentMore;
import project.namramuni.Fragement.FragmentPunyaBank;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.PlayList;
import project.namramuni.ViewModels.Main.PlayListResponse;
import project.namramuni.background.AudioService;
import project.namramuni.background.AudioServiceBinder;
import project.namramuni.background.Connectivity;
import project.namramuni.background.Constants;
import project.namramuni.background.NetworkChangeReceiver;
import project.namramuni.background.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.namramuni.background.AudioService.dbId;
import static project.namramuni.background.AudioService.mantapoint;
import static project.namramuni.background.AudioService.shilpaopenPlayer;
import static project.namramuni.background.AudioService.shilpaopenPlayer1;
import static project.namramuni.background.AudioService.shilpaplaypouse;
import static project.namramuni.background.AudioService.shilpasetSongUrl;
import static project.namramuni.background.AudioService.shilpasetimag;
import static project.namramuni.background.AudioService.shilpasettitle;
import static project.namramuni.background.AudioService.shilpasongcount;
import static project.namramuni.background.AudioServiceBinder.firsttimesongs;
import static project.namramuni.background.AudioServiceBinder.secondaryProgress;

public class MainActivity extends AppCompatActivity {
    public static BottomTabLayout navigation;
    TextView title;
    static Activity activity;
    ImageView imgSearch, imgBack;
    Toolbar toolbar;
    RecyclerView rcSearchList;
    public static FrameLayout main_fragment1, main_fragment;
    public static FragmentTransaction transaction1, transaction;
    SimpleSearchView searchView;
    Boolean doubleBackToExitPressedOnce = false;
    public static MediaPlayer audioPlayer = new MediaPlayer();
    public static AudioServiceBinder audioServiceBinder = new AudioServiceBinder();
    public static MediaPlayer shilpamediaPlayer = null;
    public static Handler audioProgressUpdateHandler = null;
    public static LinearLayout shilpamini_player_new, titleLenear;
    public static ImageView shilpaplay_song_img, shilpaplay_button_song_imageview, shilpapause_button_song_imageview;
    public static TextView shilpasong_playing_name;
    public static ProgressBar pBar;
    public static ProgressBar shilpaplay_audio_in_progressbar;
    public static Boolean mIsCollapsedFromBackPress = true;
    public static BottomSheetBehavior bottomSheetBehavior;
    public static SeekBar Bseek_bar_id;
    public static RelativeLayout BloadingPanel;
    public static TextView Btxt_playing_song_id, BsongCurrentDurationLabel, BsongTotalDurationLabel;
    public static ImageView back_to_list, Blogo_song;
    public static ImageView Bbtn_play_id, Bbtn_pause_id;
    public static Boolean bounds = false;
    NetworkChangeReceiver mNetworkReceiver;
    static long backupPos = 0;
    public static Utilities utils;
    public static RelativeLayout   shilpaloadingPanel;
    public static DivinityAdapter  divadapter;

    public static void getIntentService(Activity activity) {
        Intent serviceIntent = new Intent(activity, AudioService.class);
        serviceIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        activity.startService(serviceIntent);
        shilpaopenPlayer1 = true;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | +WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | +WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_main);
        MultiDex.install(this);
        FirebaseApp.initializeApp(this);
        activity = this;
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        findViewById();
        title = findViewById(R.id.txttitle);
        rcSearchList = findViewById(R.id.rcSearchList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        rcSearchList.setLayoutManager(linearLayoutManager2);
        rcSearchList.setHasFixedSize(true);
        imgBack = findViewById(R.id.imgBack);
        imgSearch = findViewById(R.id.imgSearch);
        SetFont.setFont(title, this, SetFont.bold);
        toolbar = findViewById(R.id.toolbar1);
        searchView = findViewById(R.id.searchView);
        setSupportActionBar(toolbar);
        getSearch();
        onclick();
        navigation = findViewById(R.id.navigation);
        main_fragment = findViewById(R.id.main_fragment);
        main_fragment1 = findViewById(R.id.main_fragment1);
        getTitles();
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // searchView.showSearch();
                Intent intent = new Intent(MainActivity.this,ActivitySeach.class);
                startActivity(intent);
            }
        });
        navigation.setItems(R.menu.navigation);
        navigation.setButtonTextStyle(R.style.TabButtonTextStyle);
        navigation.setSelectedTab(R.id.navigation_home);
        navigation.setIndicatorVisible(false);
        navigation.setListener(new BottomTabLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                if (main_fragment.getVisibility() == View.GONE) {
                    main_fragment.setVisibility(View.VISIBLE);
                    main_fragment1.setVisibility(View.GONE);
                }
                switchFragment(id, 0);
            }
        });
        utils = new Utilities();
        switchFragment(R.id.navigation_home, 0);
        /*Media player */
        getPlayingView("start");
        if (shilpamediaPlayer == null) {
            shilpamediaPlayer = new MediaPlayer();
            audioProgressUpdateHandler = new Handler();
        }
        bindAudioService();
        registerNetworkBroadcastForNougat();


    }



    public static void onclick() {
        shilpaplay_song_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    mIsCollapsedFromBackPress = false;
                } else {
                    mIsCollapsedFromBackPress = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        shilpasong_playing_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    mIsCollapsedFromBackPress = false;
                } else {
                    mIsCollapsedFromBackPress = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        shilpapause_button_song_imageview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                pause("");
            }
        });
        shilpaplay_button_song_imageview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (Connectivity.isConnectedFast(activity)) {
                    play("", "");
                } else {
                    Toast.makeText(activity, "Your internet is to slow or no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        titleLenear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    mIsCollapsedFromBackPress = false;
                } else {
                    mIsCollapsedFromBackPress = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }*/
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.e("Bottom Sheet Callback", "slideoffset: " + slideOffset);
            }
        });
        Bbtn_play_id.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (Connectivity.isConnectedFast(activity)) {
                    play("", "");
                } else {
                    Toast.makeText(activity, "Your internet is to slow or no internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bbtn_pause_id.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                pause("");
            }
        });
        back_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCollapsedFromBackPress = true;
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        Bseek_bar_id.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audioServiceBinder.seekadjust(progress);
                    Bseek_bar_id.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getPlayingView(String start) {
        try {
            if (audioPlayer != null) {
                if (!audioPlayer.isPlaying()) {
                    audioServiceBinder.storekey(dbId, shilpasettitle, shilpasetimag, shilpasetSongUrl, mantapoint, shilpasongcount);
                    setMusic();
                    audioProgressUpdateHandler = null;
                    shilpaopenPlayer = true;
                    audioServiceBinder.setAudioFileUrl(shilpasetSongUrl);
                    audioServiceBinder.setStreamAudio(true);
                    audioServiceBinder.setContext(this);
                    seekbarUpdater();
                    audioServiceBinder.setAudioProgressUpdateHandler(audioProgressUpdateHandler);
                    if (audioPlayer != null) {
                        if (!audioPlayer.isPlaying()) {
                            firsttimesongs = start;
                            play("1", "1");
                            pause("");
                            shilpamini_player_new.setVisibility(View.GONE);
                        } else {
                            shilpamini_player_new.setVisibility(View.VISIBLE);
                        }
                    } else {
                        firsttimesongs = start;
                        play("1", "1");
                        pause("");
                        shilpamini_player_new.setVisibility(View.GONE);
                    }
//                    firsttimesongs = start;
//                    play("1", "1");
//                    pause("");
                } else {
                    shilpamini_player_new.setVisibility(View.GONE);
                }
            } else {
                shilpamini_player_new.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            shilpamini_player_new.setVisibility(View.GONE);
            e.getMessage();
        }
    }

    public static void stopMusic() {
        shilpamini_player_new.setVisibility(View.GONE);
        int aa = R.id.navigation_punyabnk;
        if (aa == 2131296575)
            if (divadapter != null)
                divadapter.notifyDataSetChanged();
    }

    public static void seekbarUpdater() {
        backupPos = 0;
        if (audioProgressUpdateHandler == null) {
            audioProgressUpdateHandler = new Handler() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void handleMessage(Message msg) {
                    if (audioServiceBinder != null) {
                        try {
                            Bseek_bar_id.setProgress((int) audioServiceBinder.getAudioSeek());
                            Bseek_bar_id.setSecondaryProgress(secondaryProgress);
                            String totalTime = utils.milliSecondsToTimer(audioServiceBinder.totalAudioDuration());
                            long currentPos = audioServiceBinder.currAudioPosition();
                            BsongTotalDurationLabel.setText("" + totalTime);
                            BsongCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentPos));
                            if (currentPos == backupPos) {
                                if (audioPlayer != null) {
                                    if (audioPlayer.isPlaying()) {
                                        shilpaloadingPanel.setVisibility(View.VISIBLE);
                                        BloadingPanel.setVisibility(View.VISIBLE);
                                        Log.d("LoadingAudioSongs", "Load");
                                    } else {
                                        if (currentPos == 0) {

                                        } else {
                                            shilpaloadingPanel.setVisibility(View.GONE);
                                            BloadingPanel.setVisibility(View.GONE);
                                        }
                                    }
                                } else {
                                    if (currentPos == 0) {

                                    } else {
                                        BloadingPanel.setVisibility(View.GONE);
                                        shilpaloadingPanel.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                shilpaloadingPanel.setVisibility(View.GONE);
                                BloadingPanel.setVisibility(View.GONE);
                            }
                            backupPos = currentPos;
                            if (msg.what == audioServiceBinder.UPDATE_AUDIO_PROGRESS_BAR) {
                                int currProgress = (int) audioServiceBinder.getAudioProgress();
                                shilpaplay_audio_in_progressbar.setProgress(currProgress);
                            }
                            int aa = R.id.navigation_punyabnk;
                            if (aa == 2) {
//                                if (DiviStatus.equals("Mantra"))
                                if (divadapter != null)
                                    divadapter.notifyDataSetChanged();
                            }
                        } catch (NullPointerException e) {
                        }
                    }
                }
            };
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Application.activityResumed();
        if (audioPlayer != null) {
//            getPlayPouse();
            int aa = R.id.navigation_punyabnk;
            if (aa == 2)
                if (divadapter != null)
                    divadapter.notifyDataSetChanged();
        } else {
            stopMusic();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void musicView(String count, int point, String image, String song_name, String song_url, String mid) {
        try {
            BloadingPanel.setVisibility(View.VISIBLE);
            shilpaloadingPanel.setVisibility(View.VISIBLE);
            audioProgressUpdateHandler = null;
            audioServiceBinder.stopAudio();
            audioServiceBinder.storekey(mid, song_name, image, song_url, point, count);
            setMusic();
            audioServiceBinder.setAudioFileUrl(shilpasetSongUrl);
            audioServiceBinder.setStreamAudio(true);
            audioServiceBinder.setContext(this);
            seekbarUpdater();
            audioServiceBinder.setAudioProgressUpdateHandler(audioProgressUpdateHandler);
            shilpamini_player_new.setVisibility(View.VISIBLE);
            if (shilpaopenPlayer1 == false) {
                getIntentService(activity);
            }
            play("1", "1");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void findViewById() {
        shilpamini_player_new = findViewById(R.id.shilpamini_player_new);
        pBar = findViewById(R.id.pBar);
        shilpaplay_song_img = findViewById(R.id.shilpaplay_song_img);
        shilpasong_playing_name = findViewById(R.id.shilpasong_playing_name);
        shilpaloadingPanel = findViewById(R.id.shilpaloadingPanel);
        shilpaplay_audio_in_progressbar = findViewById(R.id.shilpaplay_audio_in_progressbar);
        shilpapause_button_song_imageview = findViewById(R.id.shilpapause_button_song_imageview);
        shilpaplay_button_song_imageview = findViewById(R.id.shilpaplay_button_song_imageview);
        titleLenear = findViewById(R.id.titleLenear);
        shilpaplay_audio_in_progressbar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        bottomSheet1();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void bottomSheet1() {
        View bottomsheet1 = findViewById(R.id.slideView);
        back_to_list = bottomsheet1.findViewById(R.id.back_to_list);
        Bseek_bar_id = bottomsheet1.findViewById(R.id.Bseek_bar_id);
        BloadingPanel = bottomsheet1.findViewById(R.id.BloadingPanel);
        Btxt_playing_song_id = bottomsheet1.findViewById(R.id.Btxt_playing_song_id);
        BsongCurrentDurationLabel = bottomsheet1.findViewById(R.id.BsongCurrentDurationLabel);
        BsongTotalDurationLabel = bottomsheet1.findViewById(R.id.BsongTotalDurationLabel);
        Blogo_song = bottomsheet1.findViewById(R.id.Blogo_song);
        Bbtn_pause_id = bottomsheet1.findViewById(R.id.Bbtn_pause_id);
        Bbtn_play_id = bottomsheet1.findViewById(R.id.Bbtn_play_id);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet1);
        Bseek_bar_id.setMax(100);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void play(String type, String s) {
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
//        getPlayPouse();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void pause(String type) {
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
            Intent intent = new Intent(MainActivity.this, AudioService.class);
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

    public static void getPlayPouse() {
        if (!audioPlayer.isPlaying()) {
            Bbtn_play_id.setVisibility(View.GONE);
            shilpaplay_button_song_imageview.setVisibility(View.GONE);
            Bbtn_pause_id.setVisibility(View.VISIBLE);
            shilpapause_button_song_imageview.setVisibility(View.VISIBLE);
        } else {
            shilpaplay_button_song_imageview.setVisibility(View.VISIBLE);
            Bbtn_play_id.setVisibility(View.VISIBLE);
            shilpapause_button_song_imageview.setVisibility(View.GONE);
            Bbtn_pause_id.setVisibility(View.GONE);
        }
    }

    public static void getPlay() {
        Bbtn_play_id.setVisibility(View.GONE);
        shilpaplay_button_song_imageview.setVisibility(View.GONE);
        Bbtn_pause_id.setVisibility(View.VISIBLE);
        shilpapause_button_song_imageview.setVisibility(View.VISIBLE);
    }

    public static void getPouse() {
        Bbtn_pause_id.setVisibility(View.GONE);
        shilpapause_button_song_imageview.setVisibility(View.GONE);
        Bbtn_play_id.setVisibility(View.VISIBLE);
        shilpaplay_button_song_imageview.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setMusic() {
        if (!shilpasetimag.isEmpty()) {
            Picasso.with(activity).load(shilpasetimag).placeholder(R.drawable.logo).into(shilpaplay_song_img, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onError() {
                    Picasso.with(activity).load(R.drawable.logo).into(shilpaplay_song_img);
                }
            });
            Picasso.with(activity).load(shilpasetimag).placeholder(R.drawable.logo).into(Blogo_song, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(activity).load(R.drawable.logo).into(Blogo_song);
                }
            });
        }
        Btxt_playing_song_id.setText(shilpasettitle);
        shilpasong_playing_name.setText(shilpasettitle);
        Bseek_bar_id.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audioServiceBinder.seekadjust(progress);
                    Bseek_bar_id.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        unBoundAudioService();
        Application.activityPaused();
        super.onDestroy();
    }

    private void getSearch() {
        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SimpleSearchView", "Submit:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SimpleSearchView", "Text changed:" + newText);
                getSearchText(newText);
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {
                Log.d("SimpleSearchView", "Text cleared");
                return false;
            }
        });
    }

    private void getSearchText(String newText) {
        if (NetworkInfo.isNetworkAvailable(this)) {
            Call<PlayListResponse> call = ApiClientMain.getApiClient().getPlayListSearch(newText);
            call.enqueue(new Callback<PlayListResponse>() {
                @Override
                public void onResponse(Call<PlayListResponse> call, Response<PlayListResponse> response) {
                    if (response != null && response.body() != null) {
                        PlayListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            List<PlayList> list = userResponse.getData();
                            Loading.Visibility(MainActivity.this, 1);
                            SongsAdapter adapter1 = new SongsAdapter(MainActivity.this, list);
                            rcSearchList.setAdapter(adapter1);
                        } else {
                            Loading.Visibility(MainActivity.this, 2);
                            Toast.makeText(MainActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(MainActivity.this, 2);
                    }
                }

                @Override
                public void onFailure(Call<PlayListResponse> call, Throwable t) {
                    Loading.Visibility(MainActivity.this, 500);
                }
            });
        } else {
            Loading.Visibility(MainActivity.this, 404);
        }
    }

    public void switchFragment(int id, int position) {
        clearstackFragment();
        navigation.setSelectedTab(id);
        switch (id) {
            case R.id.navigation_home:
                mainFragment(new FragmentHome(), position);
                break;
            case R.id.navigation_gurudev:
                mainFragment(new FragmentGurudev(), position);
                break;
            case R.id.navigation_punyabnk:
                mainFragment(new FragmentPunyaBank(), position);
                break;
//            case R.id.navigation_donate:
//                mainFragment(new FragmentDonat(), position);
//                break;
            case R.id.navigation_picture:
                mainFragment(new FragmentGallery(), position);
                break;
            case R.id.navigation_setting:
                mainFragment(new FragmentMore(), position);
                break;
        }
    }

    public void mainFragment(Fragment set, int position) {
        String TAG = main_fragment.getClass().getSimpleName();
        Bundle bundle = new Bundle();
        bundle.putString("position", String.valueOf(position));
        set.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, set, TAG);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void mainFragment1(Fragment set, String id) {
        String TAG = main_fragment.getClass().getSimpleName();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        set.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, set, TAG);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void showFragment1(Fragment setFrag) {
        String TAG = main_fragment.getClass().getSimpleName();
        Bundle bundle = new Bundle();
       /* bundle.putString("id", id);
        bundle.putString("value", value);
        bundle.putString("position", position);*/
        setFrag.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, setFrag, TAG);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void showFragment2(Fragment setFrag, EventList set) {
        main_fragment.setVisibility(View.GONE);
        main_fragment1.setVisibility(View.VISIBLE);
        String TAG = main_fragment1.getClass().getSimpleName();
        Bundle bundle = new Bundle();
        bundle.putSerializable("response", set);
        setFrag.setArguments(bundle);
        transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.main_fragment1, setFrag, TAG);
        transaction1.addToBackStack(null);
        transaction1.commitAllowingStateLoss();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBackPressed() {
        if (mIsCollapsedFromBackPress == false) {
            mIsCollapsedFromBackPress = true;
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            if (searchView.onBackPressed()) {
                searchView.closeSearch();
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed();
                        finishAffinity();
                        return;
                    }
                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Press again to close application", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                } else {
                    backstackFragment();
                }
            }
        }
    }

    public void backstackFragment() {
        Log.d("Stackcount", getSupportFragmentManager().getBackStackEntryCount() + "");
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            disableNavigationIcon();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
        }
        if (main_fragment1.getVisibility() == View.VISIBLE) {
            main_fragment.setVisibility(View.VISIBLE);
            main_fragment1.setVisibility(View.GONE);
        }
        getSupportFragmentManager().popBackStack();
        removeCurrentFragment();
    }

    public void clearstackFragment() {
        Log.d("Stackcount", getSupportFragmentManager().getBackStackEntryCount() + "");
        int a = getSupportFragmentManager().getBackStackEntryCount();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                getSupportFragmentManager().popBackStack();
                removeCurrentFragment();
            }
            disableNavigationIcon();
//            fragment.setVisibility(View.VISIBLE);
            navigation.setVisibility(View.VISIBLE);
//            main_fragment.setVisibility(View.GONE);
            getTitles();
        }
    }

    public void disableNavigationIcon() {
        imgBack.setVisibility(View.GONE);
        //        toolbar.setNavigationIcon(null);
    }

    private void removeCurrentFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        if (currentFrag != null) {
            disableNavigationIcon();
        } else {
            disableNavigationIcon();
        }
        transaction.commitAllowingStateLoss();
    }

    public void enableNavigationIcon() {
        imgBack.setVisibility(View.VISIBLE);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backstackFragment();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backstackFragment();
            }
        });
    }

    public void getTitles() {
        title.setText("Namramuni");
    }
}