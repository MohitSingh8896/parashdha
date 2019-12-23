package project.namramuni.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import project.namramuni.Adapter.SearchAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySeach extends AppCompatActivity {

    RecyclerView recyclerView;
    private ImageView img;
    private Button seachbutton;
    private EditText searchedit;
    List<RecentVideoList> list;

    String key ="2";
    String tkey ="6";
    String keyword ="testing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        recyclerView = findViewById(R.id.recyclerSearch);
        searchedit = findViewById(R.id.searchedit);
        img = findViewById(R.id.img);
        seachbutton = findViewById(R.id.seachbutton);



        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager2);
        recyclerView.setHasFixedSize(true);



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });



        seachbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyStr = searchedit.getText().toString().trim();
                if (!searchedit.getText().toString().trim().isEmpty()){
                       getSearchlist(key,tkey,keyStr);

                }else {

                }
            }
        });



    }





    private void getSearchlist(String key, String tkey,String keyword) {
//        Loading.Visibility(this);
        if (NetworkInfo.isNetworkAvailable(this)) {
            Call<NewVideoResponse> call = ApiClientMain.getApiClient().getSearch(key,tkey,keyword);

            call.enqueue(new Callback<NewVideoResponse>() {
                @Override
                public void onResponse(Call<NewVideoResponse> call, Response<NewVideoResponse> response) {
                    if (response != null && response.body() != null) {
                        NewVideoResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getRecord();

                        } else {

                            Toast.makeText(ActivitySeach.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        SearchAdapter adapter1 = new SearchAdapter (ActivitySeach.this, list);
                        recyclerView.setAdapter(adapter1);
                    } else {
                        Toast.makeText(ActivitySeach.this, "Response Error", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<NewVideoResponse> call, Throwable t) {
//                    Loading.Visibility(PlaylistActivity.this, 500);
                }
            });
        } else {
//            Loading.Visibility(PlaylistActivity.this, 404);
        }
    }

    @Override
    public void onBackPressed() {
      /*  if (youTubePlayerMain.isFullScreen())
            youTubePlayerMain.exitFullScreen();
        else*/
        finish();
    }


}
