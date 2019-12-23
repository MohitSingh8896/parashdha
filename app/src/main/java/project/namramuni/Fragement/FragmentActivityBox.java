package project.namramuni.Fragement;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Adapter.ActivityBoxAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.ViewModels.List.ActivityBoxList;
import project.namramuni.ViewModels.Main.ActivityBox;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;

public class FragmentActivityBox extends Fragment {
    @BindView(R.id.rcGurudevList)
    RecyclerView rcGurudevList;
    @BindView(R.id.lnr1)
    LinearLayout lnr1;
    @BindView(R.id.lnr2)
    LinearLayout lnr2;
    @BindView(R.id.lnr3)
    RelativeLayout lnr3;
    @BindView(R.id.a)
    TextView a;
    List<ActivityBoxList> list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpunkbank, null);
        ButterKnife.bind(this, view);
        lnr1.setVisibility(View.GONE);
        lnr2.setVisibility(View.GONE);
        lnr3.setVisibility(View.GONE);
        a.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcGurudevList.setLayoutManager(linearLayoutManager1);
        rcGurudevList.setHasFixedSize(true);
        getList(view);
        rcGurudevList.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rcGurudevList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ((MainActivity)getContext()).mainFragment1(new FragementActivityBoxContent(),list.get(position).getId());
            }
        }));
        return view;
    }
    PreferenceManager preferenceManager1;
    private void getList(View view) {
        Loading.Visibility(view);
        preferenceManager1 = new PreferenceManager(getContext(), "");
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager1.getPreferenceValues(PreferenceConstant.id);
            Call<ActivityBox> call = ApiClientMain.getApiClient().getActivityList(id);
            call.enqueue(new Callback<ActivityBox>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<ActivityBox> call, Response<ActivityBox> response) {
                    if (response != null && response.body() != null) {
                        ActivityBox userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getData();
                            Loading.Visibility(getContext(), view, 1);
                            ActivityBoxAdapter adapter1 = new ActivityBoxAdapter(getContext(), list);
                            rcGurudevList.setAdapter(adapter1);
                        } else {
                            Loading.Visibility(getContext(), view, 2);
                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<ActivityBox> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }
}
