package project.namramuni.Fragement;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Adapter.DonateAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.ViewModels.List.Donatelist;
import project.namramuni.ViewModels.Main.DonatelistResponse;

public class FragmentDonat extends Fragment {
    @BindView(R.id.switcher)
    Switch switcher;
    @BindView(R.id.rcListDonate)
    RecyclerView rcListDonate;
    @BindView(R.id.title)
    Button title;
    @BindView(R.id.lnrDonorName)
    LinearLayout lnrDonorName;
    @BindView(R.id.spinner)
    Spinner spinner;
    List<Donatelist> list = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdonate, null);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager1 = new GridLayoutManager(getContext(), 4);
        rcListDonate.setLayoutManager(linearLayoutManager1);
        rcListDonate.setHasFixedSize(true);
//        rcListDonate.setLayoutMode(RecyclerView.CHOICE_MODE_SINGLE);
        SetFont.setFont1(title, getContext(), SetFont.bold);
        getDonate(view);
        getSpin();
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    lnrDonorName.setVisibility(View.VISIBLE);
                } else {
                    lnrDonorName.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
    String item;
    private void getSpin() {
        List<String> categories = new ArrayList<String>();
        categories.add("INR");
        categories.add("US");
        categories.add("UK");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_dropdown_item, categories) {
            @SuppressLint({"WrongConstant", "ResourceAsColor"})
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(14);
//                ((TextView) v).setGravity(View.TEXT_ALIGNMENT_CENTER);
                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                return v;
            }
            @SuppressLint("WrongConstant")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTextSize(12);
                return v;
            }
        };
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getDonate(View view) {
        Loading.Visibility(view);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<DonatelistResponse> call = ApiClientMain.getApiClient().getDonateList();
            call.enqueue(new Callback<DonatelistResponse>() {
                @Override
                public void onResponse(Call<DonatelistResponse> call, Response<DonatelistResponse> response) {
                    if (response != null && response.body() != null) {
                        DonatelistResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            list = userResponse.getData();
                            if (list!=null){
                                Loading.Visibility(getContext(),view,1);
                            } else {
                                Loading.Visibility1(getContext(),view,"No Donate");
                            }
                        } else {
                            Loading.Visibility(getContext(), view,2);
                            Toast.makeText(getContext(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        try {
                            DonateAdapter adapter1 = new DonateAdapter(getContext(), list,rcListDonate);
                            rcListDonate.setAdapter(adapter1);
                        }catch (Exception e){
                            e.printStackTrace();

                        }

                    } else {
                        Toast.makeText(getContext(), "Response Error", Toast.LENGTH_LONG).show();
                        Loading.Visibility(getContext(), view,2);
                    }
                }
                @Override
                public void onFailure(Call<DonatelistResponse> call, Throwable t) {
                    Loading.Visibility(getContext(), view,500);
                }
            });
        } else {
            Loading.Visibility(getContext(), view,404);
        }
    }
}
