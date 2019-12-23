package project.namramuni.Fragement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.Adapter.EventAdapter;
import project.namramuni.Adapter.EventPageAdapter;
import project.namramuni.Adapter.NewsAdapter;
import project.namramuni.Adapter.SchedulePageAdapter;
import project.namramuni.Adapter.YoutubeRecentVideoAdapter;
import project.namramuni.Constant.BlurTransformation;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.LiveDataList;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.Main.EventResponse;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.LiveYoutubeActivity;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Activity.PDFViewerActivity;
import project.namramuni.Activity.PlaylistActivity;
import project.namramuni.Activity.QuoteActivity;
import project.namramuni.Adapter.ArticleViewAdapter;
import project.namramuni.Adapter.AudioViewAdapter;
import project.namramuni.Adapter.CustomAdapter;
import project.namramuni.Adapter.HeadingCustomAdapter;
import project.namramuni.Adapter.SongsViewAdapter;
import project.namramuni.Adapter.YoutubeVideoViewAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.CustomView.WrapContentViewPager;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.BlurBuilder;
import project.namramuni.UIDesign.CenterLayoutManager;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Bannerlist;
import project.namramuni.ViewModels.List.LiveStream;
import project.namramuni.ViewModels.List.PDFList;
import project.namramuni.ViewModels.List.Quotelist;
import project.namramuni.ViewModels.List.VideoList;
import project.namramuni.ViewModels.Main.HomeListResponse;

public class FragmentHome extends Fragment implements DiscreteScrollView.OnItemChangedListener {
    @BindView(R.id.viewPagerHeading)
    ViewPager viewPagerHeading;
    @BindView(R.id.mohitViewpage)
    ViewPager mohitViewpage;
    @BindView(R.id.scheduleViewpage)
    ViewPager scheduleViewpage;
    @BindView(R.id.pager)
    WrapContentViewPager pager;
    @BindView(R.id.pagerNews)
    ViewPager pagerNews;
    @BindView(R.id.tabDotsNews)
    TabLayout tabDotsNews;
    @BindView(R.id.mohitsedule_tabdot)
    TabLayout mohitsedule_tabdot;
    @BindView(R.id.tabDots)
    TabLayout tabDots;
    @BindView(R.id.mohitshibir_tabdot)
    TabLayout mohitshibir_tabdot;
    @BindView(R.id.lnrLiveVideo)
    RelativeLayout lnrLiveVideo;
    @BindView(R.id.rcList1)
    RecyclerView rcList1;
    @BindView(R.id.rcList2)
    RecyclerView rcList2;
    DiscreteScrollView rcList3;
    @BindView(R.id.rcList4)
    RecyclerView rcList4;
    @BindView(R.id.mohitrecntRecycle)
    RecyclerView mohitrecntRecycle;
    @BindView(R.id.subtitle1)
    TextView subtitle1;
    @BindView(R.id.viewmore)
    TextView viewmore;
    @BindView(R.id.viewAllVideo)
    TextView viewAllVideo;
    @BindView(R.id.viewAllAudio)
    TextView viewAllAudio;
    @BindView(R.id.viewAllSongs)
    TextView viewAllSongs;
    @BindView(R.id.viewAllArticles)
    TextView viewAllArticles;
    @BindView(R.id.titleLive)
    TextView titleLive;
    @BindView(R.id.txttime)
    TextView txttime;
    @BindView(R.id.liveMsg)
    TextView liveMsg;
    @BindView(R.id.sheduleMsg)
    TextView sheduleMsg;
    @BindView(R.id.shibirMsg)
    TextView shibirMsg;
    @BindView(R.id.namranumiImage)
    ImageView namranumiImage;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img1)
    ImageView img1;

    int currentPage = 0;
    Timer timer, timer1;
    final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    private ArrayList<String> youtubeVideoArrayList, youtubeTitleArrayList, youtubeTimeArrayList;
    BlurImageView songsBackround;
    List<Bannerlist> newslist = new ArrayList<>();
    List<Bannerlist> bannerlist = new ArrayList<>();
    List<VideoList> videolist = new ArrayList<>();
    List<VideoList> audiolist = new ArrayList<>();
    List<VideoList> bhakti_list = new ArrayList<>();
    List<PDFList> pdf_list = new ArrayList<>();
    List<Quotelist> Quotelist = new ArrayList<>();

    List<EventList> eventLists = new ArrayList<>();
    List<RecentVideoList> recentvideolists = new ArrayList<>();
    List<RecentVideoList> liveVideo = new ArrayList<>();
    String tkey="1",key="2";
    LiveDataList liveData;
    public static Boolean isSongs = false;
    PreferenceManager preferenceManager;
    private static final float BLUR_RADIUS = 25f;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        songsBackround = view.findViewById(R.id.songsBackround);
        preferenceManager = new PreferenceManager(getContext(), "");
        rcList3 = view.findViewById(R.id.rcList3);
        getAllList(key,tkey,view);
        getCollect(view);
        getFont(view);




        viewAllVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragment(R.id.navigation_gurudev, 0);
            }
        });
        sheduleMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ((MainActivity) getActivity()).switchFragment(R.id.navigation_gurudev, 3);
            }
        });



        viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), QuoteActivity.class)
                        .putExtra("text", "The woman, who claimed to have been associated with the the Jain monk for about 10 years, had earlier said that Namramuni used to tell her that women should surrender their hearts, body and money to the guru. She had also lodge complaints against Muni with the Prime Minister’s Office, Maharashtra chief minister Devendra Fadnavis and Maharashtra State Commission for Women.")
                        .putExtra("image", "http://parasdham.skryptech.com/public//upload/banner/a1.jpg"));


            }
        });



        viewAllAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragment(R.id.navigation_gurudev, 1);
            }
        });
        viewAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSongs = true;
                ((MainActivity) getActivity()).switchFragment(R.id.navigation_punyabnk, 0);
            }
        });
        viewAllArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).switchFragment(R.id.navigation_gurudev, 1);
            }
        });
        return view;
    }

    private void getAllList(String key, String tkey, View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            getList(tkey , key,view);
            getShedule(tkey , key,view);
            getRecentVideo(tkey , key,view);
            getliveVideo(tkey , key,view);

            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<HomeListResponse> call = ApiClientMain.getApiClient().getHome(key,tkey,id);
            call.enqueue(new Callback<HomeListResponse>() {
                @Override
                public void onResponse(Call<HomeListResponse> call, Response<HomeListResponse> response) {
                    if (response != null && response.body() != null) {
                        HomeListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            newslist = userResponse.getNews_data();
                            Quotelist = userResponse.getQuote_data();
                            liveData = userResponse.getLive_streaming();
                            videolist = userResponse.getVideo_list();
                            audiolist = userResponse.getAudio_list();
                            bhakti_list = userResponse.getBhakti_list();
                            pdf_list = userResponse.getPublication();
                            Loading.Visibility(getContext(), view, 1);
                        } else {
                            Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        getQuote(Quotelist);
                        getNews(newslist);
                        getLive(liveData);

                        getAudio(audiolist);
                        getBhakti(bhakti_list);
                        getPublication(pdf_list);


                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<HomeListResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }

    private void getNews(List<Bannerlist> newslist) {
        if (newslist != null) {
            PagerAdapter adapter = new NewsAdapter(getActivity(), newslist);
            pagerNews.setAdapter(adapter);
            tabDotsNews.setupWithViewPager(pagerNews, true);
            pagerNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    subtitle1.setText(Html.fromHtml(newslist.get(position).getNews_title().substring(0,1).toUpperCase() + newslist.get(position).getNews_title().substring(1)));
                }
                @Override
                public void onPageSelected(int position) {
                    subtitle1.setText(Html.fromHtml(newslist.get(position).getNews_title().substring(0,1).toUpperCase() + newslist.get(position).getNews_title().substring(1)));
                    //subtitle1.setText(newslist.get(position).getDescrp());
                }
                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }



    private void getList(String tkey, String key , View view) {
        if (eventLists != null) {
            eventLists.clear();
        }
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<EventResponse> call = ApiClientMain.getApiClient().getShibir(tkey, key,id);
            call.enqueue(new Callback<EventResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response != null && response.body() != null) {
                        EventResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            eventLists = userResponse.getData();
                            if (eventLists != null) {
                                PagerAdapter adapter1 = new EventPageAdapter(getContext(), eventLists);
                                mohitViewpage.setAdapter(adapter1);
                                mohitshibir_tabdot.setupWithViewPager(mohitViewpage, true);//mohitsedule_tabdot
                                shibirMsg.setVisibility(View.GONE);

                            } else {
                                Loading.Visibility(getContext(), view, "No Schedule");
                            }
                        } else {
                            Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                        shibirMsg.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }



    private void getShedule(String tkey, String key , View view) {
        if (eventLists != null) {
            eventLists.clear();
        }
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<EventResponse> call = ApiClientMain.getApiClient().getEvent(tkey, key);
            call.enqueue(new Callback<EventResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response != null && response.body() != null) {
                        EventResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("error")) {
                            eventLists = userResponse.getData();
                            if (eventLists != null) {
                                PagerAdapter adapter1 = new SchedulePageAdapter(getContext(), eventLists);
                                scheduleViewpage.setAdapter(adapter1);
                                mohitsedule_tabdot.setupWithViewPager(scheduleViewpage, true);//mohitsedule_tabdot
                                sheduleMsg.setVisibility(View.GONE);;

                            } else {
                                Loading.Visibility(getContext(), view, "No Schedule");
                           }
                        } else {
                            Loading.Visibility(getContext(), view, 2);
                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                        sheduleMsg.setVisibility(View.VISIBLE);
                    }
                }
                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }



    private void getRecentVideo(String tkey, String key , View view) {
        if (recentvideolists != null) {
            recentvideolists.clear();
        }
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<NewVideoResponse> call = ApiClientMain.getApiClient().getRecentVideo(tkey, key);
            call.enqueue(new Callback<NewVideoResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<NewVideoResponse> call, Response<NewVideoResponse> response) {
                    if (response != null && response.body() != null) {
                        NewVideoResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            recentvideolists = userResponse.getRecord();
                            if (recentvideolists != null) {
                                YoutubeRecentVideoAdapter youtubeRecentVideoAdapter = new YoutubeRecentVideoAdapter(getContext()
                                ,recentvideolists);

                                mohitrecntRecycle.setAdapter(youtubeRecentVideoAdapter);

                            } else {
                                Loading.Visibility(getContext(), view, "No Recent Video");
                            }
                        } else {
                            Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
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





    private void getPublication(List<PDFList> list) {
        if (list != null) {
            if (list.size() > 2) {
                CenterLayoutManager linearLayoutManager4 = new CenterLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rcList4.setLayoutManager(linearLayoutManager4);
                rcList4.setHasFixedSize(true);
            } else {
                LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rcList4.setLayoutManager(linearLayoutManager4);
                rcList4.setHasFixedSize(true);
            }
            ArticleViewAdapter adapter4 = new ArticleViewAdapter(getContext(), list);
            rcList4.setAdapter(adapter4);
            rcList4.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcList4, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    getContext().startActivity(new Intent(getContext(), PDFViewerActivity.class)
                            .putExtra("id", list.get(position).getId())
                            .putExtra("pdf", pdf_list.get(position).getFile_url())
                    );
                }
            }));
        }
    }

    private void getAudio(List<VideoList> audiolist) {
        AudioViewAdapter adapter2 = new AudioViewAdapter(getContext(), audiolist);
        rcList2.setAdapter(adapter2);
        rcList2.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcList2, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                getContext().startActivity(new Intent(getContext(), BhaktilistActivity.class)
                        .putExtra("name", audiolist.get(position).getPlaylist_name())
                        .putExtra("img", audiolist.get(position).getPlaylist_image())
                        .putExtra("id", audiolist.get(position).getId())
                        .putExtra("type", "Audio")
                );
            }
        }));
    }

    private void getBhakti(List<VideoList> list) {
        SongsViewAdapter adapter3 = new SongsViewAdapter(getContext(), list, songsBackround);
        rcList3.setAdapter(adapter3);
    }


//
//    private void getVideo(List<VideoList> videolist) {
//
//        YoutubeVideoViewAdapter adapter1 = new YoutubeVideoViewAdapter(getContext(), videolist);
//        rcList1.setAdapter(adapter1);
//
//
//        rcList1.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcList1, new RecyclerTouchListener.ClickListener() {
//
//
//            @Override
//            public void onClick(View view, int position) {
//                getContext().startActivity(new Intent(getContext(), PlaylistActivity.class)
//                        .putExtra("img", videolist.get(position).getPlaylist_image())
//                        .putExtra("id", videolist.get(position).getId())
//                        .putExtra("name", videolist.get(position).getPlaylist_name())
//                        .putExtra("type", "Video")
//                );
//            }
//        }));
//    }




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

                            liveVideo = userResponse.getRecord();
                                  Loading.Visibility(getContext(), view, 1);

                        } else {
                            Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if(liveVideo!=null){

                                YoutubeVideoViewAdapter adapter1 = new YoutubeVideoViewAdapter(getContext(), liveVideo);
                                rcList1.setAdapter(adapter1);

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


    private void getLive(LiveDataList liveData) {
        if (liveData == null) {
            liveMsg.setText("Coming soon");
            liveMsg.setVisibility(View.VISIBLE);
        } else {
            if (liveData.getUrl().equals(null)){
                liveMsg.setText(liveData.getMessage());
                liveMsg.setVisibility(View.VISIBLE);
            } else if(liveData.getUrl().equals("")){
                liveMsg.setText(liveData.getMessage());
                liveMsg.setVisibility(View.VISIBLE);
            } else {
                liveMsg.setVisibility(View.GONE);
                try {
                    Picasso.with(getContext()).load(R.drawable.livevideo).into(namranumiImage);
                }catch (Exception e){ }
                namranumiImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (liveData != null) {
                            startActivity(new Intent(getContext(), LiveYoutubeActivity.class)
                                    .putExtra("liveurl", liveData.getUrl())
                                    .putExtra("liveStatus", liveData.getIsLive())
                            );
                        }
                    }
                });
            }
        }
    }
    private void getQuote(List<Quotelist> quotelist) {
        PagerAdapter adapter1 = new HeadingCustomAdapter(getActivity(), quotelist);
        viewPagerHeading.setAdapter(adapter1);

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = viewPagerHeading.getCurrentItem();
                if (tab ==0){
                    tab =3;
                    viewPagerHeading.setCurrentItem(3,false);
                    viewPagerHeading.arrowScroll(View.FOCUS_LEFT);
                }else if (tab < 4){
                    tab--;
                    viewPagerHeading.setCurrentItem(tab);


                }
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = viewPagerHeading.getCurrentItem();
                tab ++;
                viewPagerHeading.setCurrentItem(tab);
                if (tab == 4){
                    viewPagerHeading.setCurrentItem(0,false);
                    viewPagerHeading.arrowScroll(View.FOCUS_LEFT);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCollect(View view) {
        rcList1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcList1.setLayoutManager(linearLayoutManager1);
        rcList2.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcList2.setLayoutManager(linearLayoutManager2);
        rcList3.setOrientation(DSVOrientation.HORIZONTAL);
        rcList3.addOnItemChangedListener(this);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        mohitrecntRecycle.setLayoutManager(linearLayoutManager3);
        mohitrecntRecycle.setHasFixedSize(true);


    }




    private void getFont(View view) {
        SetFont.setFont((TextView) view.findViewById(R.id.videoBold), getContext(), SetFont.font8);
        //SetFont.setFont((TextView) view.findViewById(R.id.videoNormal), getContext(), SetFont.font5);
        SetFont.setFont((TextView) view.findViewById(R.id.audioBold), getContext(), SetFont.font8);
        SetFont.setFont((TextView) view.findViewById(R.id.audioNormal), getContext(), SetFont.font11);
        SetFont.setFont((TextView) view.findViewById(R.id.bhaktiBold), getContext(), SetFont.font8);
      //  SetFont.setFont((TextView) view.findViewById(R.id.bhaktiNormal), getContext(), SetFont.font11);
        SetFont.setFont((TextView) view.findViewById(R.id.artipubBold), getContext(), SetFont.font8);
       // SetFont.setFont((TextView) view.findViewById(R.id.artipubNormal), getContext(), SetFont.font11);
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        try {
                    Glide.with(getActivity().getApplicationContext())
                    .load(bhakti_list.get(i).getPlaylist_image())
                    .transform(new BlurTransformation(getActivity()))
                    .error(R.drawable.logo)
                    .into(songsBackround);

        } catch (Exception e) {
        }
    }

}