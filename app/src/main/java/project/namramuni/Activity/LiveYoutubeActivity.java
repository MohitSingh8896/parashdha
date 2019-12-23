package project.namramuni.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import project.namramuni.R;

public class LiveYoutubeActivity extends AppCompatActivity {
    ImageView imgBack;
    String test = "C6qLPYlGITU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtub_live);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String url = getIntent().getStringExtra("liveurl")/*"n8T9x6Y2y80"*/;

        Boolean liveStatus = getIntent().getBooleanExtra("liveStatus", false);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        YouTubePlayerView youTubePlayerView1 = (YouTubePlayerView) findViewById(R.id.youtube_player1);

        if (liveStatus == true) {

            /*youTubePlayerView1.setVisibility(View.VISIBLE);
            getLifecycle().addObserver(youTubePlayerView1);
            youTubePlayerView1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, getLifecycle(), url, 0f);
                }
            });*/

            youTubePlayerView.setVisibility(View.VISIBLE);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, getLifecycle(), url, 0f);
                }
            });
        } else {
            youTubePlayerView1.setVisibility(View.VISIBLE);
            getLifecycle().addObserver(youTubePlayerView1);
            youTubePlayerView1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, getLifecycle(), url, 0f);
                }
            });
        }

    }
}