package project.namramuni.Fragement;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Constant.ApplicationData;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Adapter.EventAdapter;
import project.namramuni.Adapter.ShibirAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.Main.EventResponse;

import static project.namramuni.Adapter.EventPageAdapter.isShibir;

public class FragmentEvents extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    List<EventList> list;
    @BindView(R.id.rlvShibir)
    RelativeLayout rlvShibir;
    @BindView(R.id.imgShibir)
    ImageView imgShibir;
    @BindView(R.id.txtShibir)
    TextView txtShibir;
    @BindView(R.id.rlvEvent)
    RelativeLayout rlvEvent;
    @BindView(R.id.imgEvent)
    ImageView imgEvent;
    @BindView(R.id.txtEvent)
    TextView txtEvent;
    @BindView(R.id.txtNoData)
     TextView txtNoData;
    int s;
    String tkey ="5";
    String key ="8";

    PreferenceManager preferenceManager;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentevent, null);
        ButterKnife.bind(this, view);
        lnr1.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        txtNoData.setVisibility(View.GONE);
        preferenceManager = new PreferenceManager(getContext(), "");

        getList(tkey , key,view);

        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (s == 1) {
                    ((MainActivity) getActivity()).showFragment2(new FragmentEvent(), list.get(position));
                    Toast.makeText(getActivity(), ""+list.get(position), Toast.LENGTH_SHORT).show();

                }  else {

                }



            }
        }));


        rlvShibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab(2, view);

            }
        });
        rlvEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTab(1, view);
            }
        });

        if (isShibir == true){
            setTab(2, view);
        }else {
            setTab(s, view);
        }
        isShibir = false;
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTab(int s, View view) {
        this.s = s;
        if (s == 1) {
            rlvShibir.setBackgroundResource(R.drawable.read2);
            rlvEvent.setBackgroundResource(R.drawable.read1);
            imgEvent.setColorFilter(getContext().getColor(R.color.white));
            txtEvent.setTextColor(getContext().getColor(R.color.white));
            imgShibir.setColorFilter(getContext().getColor(R.color.heading));
            txtShibir.setTextColor(getContext().getColor(R.color.heading));



            getList(tkey,key,view);
        } else if (s == 2) {
            rlvShibir.setBackgroundResource(R.drawable.read1);
            rlvEvent.setBackgroundResource(R.drawable.read2);
            imgShibir.setColorFilter(getContext().getColor(R.color.white));
            txtShibir.setTextColor(getContext().getColor(R.color.white));
            imgEvent.setColorFilter(getContext().getColor(R.color.heading));
            txtEvent.setTextColor(getContext().getColor(R.color.heading));


            getList1(tkey,key,view);
        }
    }





    private void getList(String tkey, String key , View view) {
        if (list != null) {
            list.clear();
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
                            list = userResponse.getData();
                            if (list != null) {
                                Loading.Visibility(getContext(), view, 1);
                                EventAdapter adapter1 = new EventAdapter(getContext(), list);
                                rcGurudevList.setAdapter(adapter1);
                                txtNoData.setVisibility(View.GONE);

                            } else {
                                Loading.Visibility(getContext(), view, "No Schedule");
                            }
                        } else {
                            Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                        txtNoData.setVisibility(View.VISIBLE);
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

    private void getList1(String tkey, String key  ,View view) {
        if (list != null) {
            list.clear();
        }
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
           String  id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<EventResponse> call = ApiClientMain.getApiClient().getShibir(tkey,key,id);
            call.enqueue(new Callback<EventResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response != null && response.body() != null) {
                        EventResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getData();
                            if (list != null) {
                                Loading.Visibility(getContext(), view, 1);
                                ShibirAdapter adapter1 = new ShibirAdapter(getContext(), list);
                                rcGurudevList.setAdapter(adapter1);
                                txtNoData.setVisibility(View.GONE);
                            } else {
                                Loading.Visibility(getContext(), view, "No Shibir");
                            }

                        } else {
                            Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                        txtNoData.setVisibility(View.VISIBLE);
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
}
