package project.namramuni.Fragement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.Shibirformlist;
import project.namramuni.ViewModels.Main.EventResponse;
import project.namramuni.ViewModels.Main.ShibirFormlistResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentLucyDraw extends Fragment {
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.imgprogressBar)
    ProgressBar imgprogressBar;

    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.titlelucytxt)
    TextView titlelucytxt;
    @BindView(R.id.setlucknumber)
    TextView setlucknumber;

    @BindView(R.id.mgstxt)
    TextView mgstxt;

    @BindView(R.id.titlelucy)
    TextView titlelucy;
    @BindView(R.id.relyoutupdate)
    RelativeLayout relyoutupdate;
    @BindView(R.id.relativegenunver)
    RelativeLayout relativegenunver;
    String [] countriesList = {"Event/Mandap","Live" };
    PreferenceManager preferenceManager;


    String tkey="1",key="2";
    String spinervaleus="";
    String luckydraw_idStr=  "";
    String event_idStr=  "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lucy_draw, null);
        ButterKnife.bind(this, view);

        preferenceManager = new PreferenceManager(getContext(), "");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countriesList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        getLucky(tkey , key);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    spinner1.getSelectedItemPosition();
                    if ( spinner1.getSelectedItemPosition()== 0){
                        spinervaleus = "E";
                    }else if (spinner1.getSelectedItemPosition() ==1){
                        spinervaleus = "L";
                    }

                  }

                public void onNothingSelected(AdapterView<?> arg0) {                }

            });

          submit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                   if (event_idStr.equals("")){
                       Toast.makeText(getActivity(), "Event id not found", Toast.LENGTH_SHORT).show();
                   }else if (luckydraw_idStr.equals("")){
                       Toast.makeText(getActivity(), "Lucky Draw  id not found", Toast.LENGTH_SHORT).show();
                   }else if (spinervaleus.equals("")){
                       Toast.makeText(getActivity(), "Event Status not found", Toast.LENGTH_SHORT).show();
                   }else {
                       UpdateLucyDraw( key, tkey, spinervaleus, event_idStr, luckydraw_idStr);

                   }


              }
          });



        return  view;
    }





    public void   UpdateLucyDraw(String key,String tkey,String user_status,String event_id,String luckydraw_id) {

        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            imgprogressBar.setVisibility(View.VISIBLE);

            Call<ShibirFormlistResponse> call = ApiClientMain.getApiClient().UpdateLucyDraw(key,tkey,user_status,event_id,luckydraw_id,id);
            call.enqueue(new Callback<ShibirFormlistResponse>() {
                @Override
                public void onResponse(Call<ShibirFormlistResponse> call, Response<ShibirFormlistResponse> response) {

                    if (response != null && response.body() != null) {
                        ShibirFormlistResponse userResponse = response.body();
                        Shibirformlist formlist = userResponse.getData();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                imgprogressBar.setVisibility(View.GONE);

                                relativegenunver.setVisibility(View.VISIBLE);
                                relyoutupdate.setVisibility(View.GONE);

                                mgstxt.setVisibility(View.GONE);

                                getLucky(tkey , key);

                                Toast.makeText(getActivity(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            imgprogressBar.setVisibility(View.GONE);


                        }
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ShibirFormlistResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
                    imgprogressBar.setVisibility(View.GONE);

                }
            });
        } else {
            Toast.makeText(getContext(), "Check your internet", Toast.LENGTH_SHORT).show();
            imgprogressBar.setVisibility(View.GONE);

        }
    }



    private void getLucky(String key, String tkey) {
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            Call<ShibirFormlistResponse> call = ApiClientMain.getApiClient().getLuckydraw(key,tkey,id);
            call.enqueue(new Callback<ShibirFormlistResponse>() {
                @Override
                public void onResponse(Call<ShibirFormlistResponse> call, Response<ShibirFormlistResponse> response) {
                    if (response != null && response.body() != null) {
                        ShibirFormlistResponse userResponse = response.body();
                        Shibirformlist formlist = userResponse.getData();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                 String setlucknumberStr =  formlist.getLucky_number();
                                 String event_titleStr=  formlist.getEvent_title();
                                 luckydraw_idStr=  formlist.getLuckydraw_id();
                                 event_idStr=  formlist.getEvent_id();
                                 titlelucy.setText(event_titleStr);
                                 titlelucytxt.setText(event_titleStr);

                                if (setlucknumberStr.equals("")){
                                    setlucknumber.setText("");
                                    mgstxt.setVisibility(View.GONE);
                                    relativegenunver.setVisibility(View.GONE);
                                    relyoutupdate.setVisibility(View.VISIBLE);
                                }else {
                                    setlucknumber.setText(setlucknumberStr);
                                    mgstxt.setVisibility(View.GONE);
                                    relyoutupdate.setVisibility(View.GONE);
                                    relativegenunver.setVisibility(View.VISIBLE);
                                }
                                imgprogressBar.setVisibility(View.GONE);

                            }catch (Exception e) {
                                   e.getMessage();
//                                imgprogressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else if (userResponse.getStatus().equals("error")){

                            mgstxt.setVisibility(View.VISIBLE);
                            relativegenunver.setVisibility(View.GONE);
                            relyoutupdate.setVisibility(View.GONE);
                            imgprogressBar.setVisibility(View.GONE);

                        }else {
                            Toast.makeText(getActivity(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                      relativegenunver.setVisibility(View.GONE);
//                                      relyoutupdate.setVisibility(View.VISIBLE);

                        }
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ShibirFormlistResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
//                    imgprogressBar.setVisibility(View.GONE);

                }
            });
        } else {
            Toast.makeText(getContext(), "Check your internet", Toast.LENGTH_SHORT).show();
//            imgprogressBar.setVisibility(View.GONE);

        }
    }







    @Override
    public void onDetach() {
        super.onDetach();

    }


}
