package project.namramuni.Fragement;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Adapter.ContactAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.ContactList;
import project.namramuni.ViewModels.Main.ContactListResponse;

public class FragmentContact extends Fragment {
    @BindView(R.id.rcList)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.contactfragemnt, null);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).enableNavigationIcon();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getList(view);
        return view;
    }

    private void getList(View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<ContactListResponse> call = ApiClientMain.getApiClient().getContact();
            call.enqueue(new Callback<ContactListResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<ContactListResponse> call, Response<ContactListResponse> response) {
                    if (response != null && response.body() != null) {
                        ContactListResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            List<ContactList> headlist = userResponse.getData();
                            Loading.Visibility(getContext(), view, 1);
                            ContactAdapter adapter = new ContactAdapter(getContext(),headlist);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Loading.Visibility(getContext(), view, 2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Loading.Visibility(getContext(), view, 2);
                    }
                }

                @Override
                public void onFailure(Call<ContactListResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view, 404);
        }
    }

}
