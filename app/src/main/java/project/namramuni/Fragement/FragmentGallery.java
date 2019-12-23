package project.namramuni.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Adapter.Gallery1Adapter;
import project.namramuni.Adapter.GalleryAdapter;
import project.namramuni.Adapter.ImageSliderAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Gallerylist;
import project.namramuni.ViewModels.Main.GallerylistResponse;

public class FragmentGallery extends Fragment {
    @BindView(R.id.rcGalleryList)
    RecyclerView rcGalleryList;
    @BindView(R.id.rcGalleryList1)
    RecyclerView rcGalleryList1;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.btnTitle)
    Button btnTitle;
    @BindView(R.id.viewPhoto)
    ViewPager viewPhoto;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, null);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3);
        rcGalleryList.setLayoutManager(linearLayoutManager);
        rcGalleryList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new GridLayoutManager(getContext(), 3);
        rcGalleryList1.setLayoutManager(linearLayoutManager1);
        rcGalleryList1.setHasFixedSize(true);
        SetFont.setFont1(btnTitle,getContext(),SetFont.bold);
        getListing(view);
        setGallery();
        rcGalleryList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGalleryList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view1, int position) {
                getSubListing(view,list.get(position).getId());
                setPhotoGallery(list.get(position).getTitle_name());
            }
        }));
        rcGalleryList1.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGalleryList1, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                setFullPhoto(position);
            }
        }));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPhoto.getVisibility()==View.VISIBLE){
                    viewPhoto.setVisibility(View.GONE);
                } else {
                    setGallery();
                }
            }
        });
        return view;
    }

    private void getListing(View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<GallerylistResponse> call = ApiClientMain.getApiClient().getGalleryList();
            call.enqueue(new Callback<GallerylistResponse>() {
                @Override
                public void onResponse(Call<GallerylistResponse> call, Response<GallerylistResponse> response) {
                    if (response != null && response.body() != null) {
                        GallerylistResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getData();
                            Loading.Visibility(getContext(),view,1);
                        } else {
                            Loading.Visibility(getContext(), view,2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        GalleryAdapter adapter = new GalleryAdapter(getContext(), list);
                        rcGalleryList.setAdapter(adapter);
                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view,2);
                    }
                }
                @Override
                public void onFailure(Call<GallerylistResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view,500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view,404);
        }
    }
    private void getSubListing(View view,String id) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<GallerylistResponse> call = ApiClientMain.getApiClient().getSubGalleryList(id);
            call.enqueue(new Callback<GallerylistResponse>() {
                @Override
                public void onResponse(Call<GallerylistResponse> call, Response<GallerylistResponse> response) {
                    if (response != null && response.body() != null) {
                        GallerylistResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            sublist = userResponse.getData();
                            Loading.Visibility(getContext(),view,1);
                        } else {
                            Loading.Visibility(getContext(), view,2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        Gallery1Adapter adapter1 = new Gallery1Adapter(getContext(), sublist);
                        rcGalleryList1.setAdapter(adapter1);
                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view,2);
                    }
                }
                @Override
                public void onFailure(Call<GallerylistResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view,500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view,404);
        }
    }

    private void setFullPhoto(int position) {
        viewPhoto.setAdapter(new ImageSliderAdapter(getContext(), sublist));
        viewPhoto.setCurrentItem(position);
        viewPhoto.setVisibility(View.VISIBLE);
    }
    List<Gallerylist> list = new ArrayList<>();
    List<Gallerylist> sublist = new ArrayList<>();
    private void setGallery() {
        btnTitle.setText("Gallery");
        imgBack.setVisibility(View.GONE);
        viewPhoto.setVisibility(View.GONE);
        rcGalleryList1.setVisibility(View.GONE);
        rcGalleryList.setVisibility(View.VISIBLE);
    }

    private void setPhotoGallery(String title_name) {
        btnTitle.setText(title_name);
        rcGalleryList.setVisibility(View.GONE);
        imgBack.setVisibility(View.VISIBLE);
        rcGalleryList1.setVisibility(View.VISIBLE);
    }

/*
    private ArrayList<ReadPojo> getList() {
        ArrayList<ReadPojo> watchPojos = new ArrayList<>();
        watchPojos.add(new ReadPojo("Educational Support", R.drawable.a1));
        watchPojos.add(new ReadPojo("Army Welfare Fund", R.drawable.a2));
        watchPojos.add(new ReadPojo("Community Welfare", R.drawable.a3));
        watchPojos.add(new ReadPojo("Disaster Relief", R.drawable.a4));
        watchPojos.add(new ReadPojo("Medical Support", R.drawable.a5));
        watchPojos.add(new ReadPojo("Ashram Support", R.drawable.a6));
        watchPojos.add(new ReadPojo("Animal Welfare", R.drawable.a7));
        watchPojos.add(new ReadPojo("Dialysis Sahay", R.drawable.a8));
        watchPojos.add(new ReadPojo("Arham Jal Mandir", R.drawable.a9));
        watchPojos.add(new ReadPojo("Educational Support", R.drawable.a1));
        watchPojos.add(new ReadPojo("Army Welfare Fund", R.drawable.a2));
        watchPojos.add(new ReadPojo("Community Welfare", R.drawable.a3));
        watchPojos.add(new ReadPojo("Disaster Relief", R.drawable.a4));
        watchPojos.add(new ReadPojo("Medical Support", R.drawable.a5));
        watchPojos.add(new ReadPojo("Ashram Support", R.drawable.a6));
        watchPojos.add(new ReadPojo("Animal Welfare", R.drawable.a7));
        watchPojos.add(new ReadPojo("Dialysis Sahay", R.drawable.a8));
        watchPojos.add(new ReadPojo("Arham Jal Mandir", R.drawable.a9));
        watchPojos.add(new ReadPojo("Educational Support", R.drawable.a1));
        watchPojos.add(new ReadPojo("Army Welfare Fund", R.drawable.a2));
        watchPojos.add(new ReadPojo("Community Welfare", R.drawable.a3));
        watchPojos.add(new ReadPojo("Disaster Relief", R.drawable.a4));
        watchPojos.add(new ReadPojo("Medical Support", R.drawable.a5));
        watchPojos.add(new ReadPojo("Ashram Support", R.drawable.a6));
        watchPojos.add(new ReadPojo("Animal Welfare", R.drawable.a7));
        watchPojos.add(new ReadPojo("Dialysis Sahay", R.drawable.a8));
        watchPojos.add(new ReadPojo("Arham Jal Mandir", R.drawable.a9));
        watchPojos.add(new ReadPojo("Educational Support", R.drawable.a1));
        watchPojos.add(new ReadPojo("Army Welfare Fund", R.drawable.a2));
        watchPojos.add(new ReadPojo("Community Welfare", R.drawable.a3));
        watchPojos.add(new ReadPojo("Disaster Relief", R.drawable.a4));
        watchPojos.add(new ReadPojo("Medical Support", R.drawable.a5));
        watchPojos.add(new ReadPojo("Ashram Support", R.drawable.a6));
        watchPojos.add(new ReadPojo("Animal Welfare", R.drawable.a7));
        watchPojos.add(new ReadPojo("Dialysis Sahay", R.drawable.a8));
        watchPojos.add(new ReadPojo("Arham Jal Mandir", R.drawable.a9));
        return watchPojos;
    }
*/
}
