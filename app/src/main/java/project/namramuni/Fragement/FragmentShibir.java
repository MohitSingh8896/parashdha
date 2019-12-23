package project.namramuni.Fragement;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.RequestQueue;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.LoginActivity;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Activity.OTPActivity;
import project.namramuni.Adapter.YoutubeRecentVideoAdapter;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.BlurTransformation;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.List.Shibirformlist;
import project.namramuni.ViewModels.List.UserList;
import project.namramuni.ViewModels.Main.EventResponse;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import project.namramuni.ViewModels.Main.ShibirFormlistResponse;
import project.namramuni.ViewModels.Main.UserResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentShibir extends Fragment {
    @BindView(R.id.edtDOB)
    EditText edtDOB;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.profImage)
    ImageView profImage;
    @BindView(R.id.imgprogressBar)
    ProgressBar imgprogressBar;
    @BindView(R.id.cardView1)
    RelativeLayout cardView1;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.edtAge)
    EditText edtAge;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtCity)
    EditText edtCity;
    @BindView(R.id.edtState)
    EditText edtState;
    @BindView(R.id.edpincode)
    EditText edpincode;
    @BindView(R.id.edtno)
    EditText edtno;
    @BindView(R.id.destab)
    TableRow destab;
    @BindView(R.id.edtEducation)
    EditText edtEducation;
    @BindView(R.id.editmc_qus1)
    EditText editmc_qus1;
    @BindView(R.id.editmc_qus2)
    EditText editmc_qus2;
     @BindView(R.id.editmc_qus3)
    EditText editmc_qus3;
     @BindView(R.id.editmc_qus4)
    EditText editmc_qus4;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.close1)
    ImageView close1;
    @BindView(R.id.checkNo)
    RadioButton checkNo;
    @BindView(R.id.checkYes)
    RadioButton checkYes;
    @BindView(R.id.check1Yes2)
    RadioButton check1Yes2;
    @BindView(R.id.check1No2)
    RadioButton check1No2;
    @BindView(R.id.check1Yes3)
    RadioButton check1Yes3;
    @BindView(R.id.check1No3)
    RadioButton check1No3;
    @BindView(R.id.check1Yes4)
    RadioButton check1Yes4;
    @BindView(R.id.check1No4)
    RadioButton check1No4;
    @BindView(R.id.marriedYes)
    RadioButton marriedYes;
    @BindView(R.id.marriedNo)
    RadioButton marriedNo;
    @BindView(R.id.txtTitleName)
    TextView txtTitleName;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.mc_qus1)
    TextView mc_qus1;
    @BindView(R.id.mc_qus2)
    TextView mc_qus2;
    @BindView(R.id.mc_qus3)
    TextView mc_qus3;
    @BindView(R.id.mc_qus4)
    TextView mc_qus4;
    @BindView(R.id.mc_qus1_tab1)
    TableRow mc_qus1_tab1;
    @BindView(R.id.editmc_qus1_tab1)
    TableRow editmc_qus1_tab1;
    @BindView(R.id.mc_qus1_tab2)
    TableRow mc_qus1_tab2;
    @BindView(R.id.editmc_qus1_tab2)
    TableRow editmc_qus1_tab2;
    @BindView(R.id.mc_qus1_tab3)
    TableRow mc_qus1_tab3;
    @BindView(R.id.editmc_qus1_tab3)
    TableRow editmc_qus1_tab3;
    @BindView(R.id.mc_qus1_tab4)
    TableRow mc_qus1_tab4;
    @BindView(R.id.editmc_qus1_tab4)
    TableRow editmc_qus1_tab4;
    @BindView(R.id.getshibitvenues)
    TextView getshibitvenues;
    @BindView(R.id.txtque_one)
    TextView txtque_one;
    @BindView(R.id.que_two)
    TextView txtque_two;
    @BindView(R.id.editque_three)
    TextView editque_three;
    @BindView(R.id.txtqus_four)
    TextView txtqus_four;
    @BindView(R.id.msustiontab4)
    TableRow msustiontab4;
    @BindView(R.id.msustiontab1)
    TableRow msustiontab1;
    @BindView(R.id.msustiontab2)
    TableRow msustiontab2;
    @BindView(R.id.msustiontab3)
    TableRow msustiontab3;
    @BindView(R.id.state_spinner)
    Spinner state_spinner;

    @BindView(R.id.city_spinner)
    Spinner city_spinner;




    String shibirid="";

    private int mYear, mMonth, mDay;
    SimpleDateFormat dateFormat;
    PreferenceManager preferenceManager;
    String key ="2", tkey ="3";
            String Strmc_yes1 ="";
            String Strmc_yes2 ="";
            String Strmc_yes3 ="";
            String Strmc_yes4 ="";
            String Strmarried="";

    String country_id ="101";
    List<EventList> satelist;

    List<String> listSpinner = new ArrayList<>();
    List<String> statelistid = new ArrayList<>();

    List<String> citySpinnerlsit = new ArrayList<>();
    List<String> citylistid = new ArrayList<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shibir, null);
        ButterKnife.bind(this, view);
        preferenceManager = new PreferenceManager(getContext(), "");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).backstackFragment();
            }
        });
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtDOB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edtDOB.getRight() - edtDOB.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        getData();
                        return true;
                    }
                }
                return false;
            }
        });
        checkNo.setChecked(false);
        checkYes.setChecked(false);
        check1Yes2.setChecked(false);
        check1No2.setChecked(false);
        check1Yes3.setChecked(false);
        check1No3.setChecked(false);
        check1Yes4.setChecked(false);
        check1No4.setChecked(false);

        check1No4.setChecked(false);
        marriedYes.setChecked(false);
        marriedNo.setChecked(false);



        EventList dene = (EventList) getArguments().getSerializable("response");
        txtTitleName.setText(dene.getShibir_title());
        String date1= dene.getStart_from();
        String date2= dene.getEnd_to();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
              dt = format.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat your_format = new SimpleDateFormat("dd-MMM-yyyy");
    //  enddate
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        try {
            dt1 = format1.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat your_format1 = new SimpleDateFormat("dd-MMM-yyyy");
        String startdate = your_format.format(dt);
        String Enddate = your_format1.format(dt1);



        txtDate.setText("Shibir Date : "+startdate+" To "+Enddate);

         shibirid = dene.getId();

        if (shibirid !=null){
            getDatalist(key,tkey,shibirid);
            getStatelist(key,tkey,country_id);
        }else {

        }






        editmc_qus1.setVisibility(View.GONE);
        editmc_qus2.setVisibility(View.GONE);
        editmc_qus3.setVisibility(View.GONE);
        editmc_qus4.setVisibility(View.GONE);

        mc_qus1.setVisibility(View.GONE);
        mc_qus2.setVisibility(View.GONE);
        mc_qus3.setVisibility(View.GONE);
        mc_qus4.setVisibility(View.GONE);


        mc_qus1_tab1.setVisibility(View.GONE);
        editmc_qus1_tab1.setVisibility(View.GONE);
        mc_qus1_tab2.setVisibility(View.GONE);
        editmc_qus1_tab2.setVisibility(View.GONE);
        mc_qus1_tab3.setVisibility(View.GONE);
        editmc_qus1_tab3.setVisibility(View.GONE);
        mc_qus1_tab4.setVisibility(View.GONE);
        editmc_qus1_tab4.setVisibility(View.GONE);
        destab.setVisibility(View.GONE);
        msustiontab4.setVisibility(View.GONE);
        msustiontab1.setVisibility(View.GONE);
        msustiontab2.setVisibility(View.GONE);
        msustiontab3.setVisibility(View.GONE);



        marriedYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marriedNo.setChecked(false);
                marriedYes.setChecked(true);
                Strmarried="yes";

            }
        });
        marriedNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marriedYes.setChecked(false);
                marriedNo.setChecked(true);
                Strmarried="no";
            }
        });


        checkYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNo.setChecked(false);
                checkYes.setChecked(true);

             Strmc_yes1="yes";




            }
        });
        checkNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkYes.setChecked(false);
                checkNo.setChecked(true);
                Strmc_yes1="no";

            }
        });


        check1Yes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1No2.setChecked(false);
                check1Yes2.setChecked(true);
                Strmc_yes2="yes";


            }
        });
        check1No2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1Yes2.setChecked(false);
                check1No2.setChecked(true);
                Strmc_yes2="no";


            }
        });

        check1Yes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1Yes3.setChecked(true);
                check1No3.setChecked(false);

                Strmc_yes3="yes";


            }
        });
        check1No3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1Yes3.setChecked(false);
                check1No3.setChecked(true);

                Strmc_yes3="no";


            }
        });

        check1Yes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1Yes4.setChecked(true);
                check1No4.setChecked(false);
               Strmc_yes4="yes";

            }
        });
        check1No4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1Yes4.setChecked(false);
                check1No4.setChecked(true);
                Strmc_yes4="no";

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = dene.getId();

                String startDateStrNewFormat ="";
                String  name = edtName.getText().toString();
                String  address = edtAddress.getText().toString();
                String  city = edtCity.getText().toString();
                String  state = edtState.getText().toString();
                String  pincode = edpincode.getText().toString();
                String  contact_no = edtno.getText().toString();

                String  age = edtAge.getText().toString();
                String  marrital_status = Strmarried;
                String  occupation = edtEducation.getText().toString();
                String  ans_one = editmc_qus1.getText().toString();
                String  ans_two = editmc_qus2.getText().toString();
                String  ans_three = editmc_qus3.getText().toString();
                String  ans_four = editmc_qus4.getText().toString();
                String  mc_ans_one =Strmc_yes1;
                String  mc_ans_two = Strmc_yes2;
                String  mc_ans_three = Strmc_yes3;
                String  mc_ans_four = Strmc_yes4;

//                String  dob = edtDOB.getText().toString();

                String startDateStr =edtDOB.getText().toString();
                DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                try {
                    date1 = inputFormat.parse(startDateStr);
                     startDateStrNewFormat = outputFormat.format(date1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

               if (shibirid != null){
                   PostFormDetail(key,tkey,shibirid,name,address,city,state,pincode,contact_no,startDateStrNewFormat,age,marrital_status,occupation,ans_one,ans_two,ans_three,ans_four,mc_ans_one,mc_ans_two,mc_ans_three,mc_ans_four);
               }


            }
        });
        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).backstackFragment();
            }
        });



        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  state_id=  statelistid.get(i);
                getCitylist(tkey, key,state_id);
                Toast.makeText(getActivity(), "SELECT STATE"+state_id, Toast.LENGTH_SHORT).show();

//                if (state_id.equals("")){
//                    Toast.makeText(getActivity(), "SELECT STATE", Toast.LENGTH_SHORT).show();
//
//                }else {
//                   getCitylist(tkey, key,state_id);
//
//                    Toast.makeText(getActivity(), "SELECT STATE"+state_id, Toast.LENGTH_SHORT).show();
//                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return view;
    }
    private void getData() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                String date = edtDOB.getText().toString();
                try {
                    c.setTime(dateFormat.parse(date));
                    int age = now.get(Calendar.YEAR) - c.get(Calendar.YEAR);
                    if (now.get(Calendar.DAY_OF_YEAR) < c.get(Calendar.DAY_OF_YEAR)) {
                        age--;
                    }
                    String idade = Integer.toString(age);
                    edtAge.setText(idade);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public static String data = "";


    private void getDatalist(String key, String tkey, String shibirid) {

        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);

            Call<ShibirFormlistResponse> call = ApiClientMain.getApiClient().getShibirForm(key,tkey,shibirid,id);
            call.enqueue(new Callback<ShibirFormlistResponse>() {
                @Override
                public void onResponse(Call<ShibirFormlistResponse> call, Response<ShibirFormlistResponse> response) {
                    if (response != null && response.body() != null) {
                        ShibirFormlistResponse userResponse = response.body();
                        Shibirformlist formlist = userResponse.getData();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                String   startDateStrNewFormat="";
                                 edtName.setText(formlist.getName());
                                 edtAddress.setText(formlist.getAddress());
                                 getshibitvenues.setText("Venue :"+formlist.getShibir_venue());
                                 edpincode.setText(formlist.getPincode());
                                 edtCity.setText(formlist.getCity());
                                 edtno.setText(formlist.getContact_no());
                                 edtState.setText(formlist.getState());
                                 edtAge.setText(formlist.getAge());
                                 edtEducation.setText(formlist.getOccupation());
                                 String marritalStr = formlist.getMarrital_status();

                                if (formlist.getDob() !=null) {
                                    String startDateStr = formlist.getDob();
                                    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    Date date1 = null;
                                    try {
                                        date1 = inputFormat.parse(startDateStr);
                                        startDateStrNewFormat = outputFormat.format(date1);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    edtDOB.setText(startDateStrNewFormat);

                                }else {
                                    edtDOB.setText(startDateStrNewFormat);
                                }

                                 if (formlist.getDescription()!=null){
                                        destab.setVisibility(View.VISIBLE);
                                        description.setText(formlist.getDescription());
                                  }else {
                                     destab.setVisibility(View.GONE);
                                     description.setVisibility(View.GONE);
                                  }

                                 if (formlist.getProfile_image() != null) {
//
                                    Picasso.with(getContext()).load(formlist.getProfile_image()).placeholder(R.drawable.profile)
                                            .error(R.drawable.profile).into(profImage, new com.squareup.picasso.Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    });

                                }else {

                                    Picasso.with(getContext()).load(R.drawable.profile).placeholder(R.drawable.profile).error(R.drawable.profile).into(profImage);
                                }


                                 String mcQ1 = formlist.getMc_que_one();
                                 String mcQ2 = formlist.getMc_que_two();
                                 String mcQ3 = formlist.getMc_que_three();
                                 String mcQ4 = formlist.getMc_que_four();

                                String que_one = formlist.getQue_one();
                                String que_two = formlist.getQue_two();
                                String que_three = formlist.getQue_three();
                                String que_four = formlist.getQue_four();

                                if (marritalStr.equals("yes")){
                                    marriedYes.setChecked(true);
                                    marriedNo.setChecked(false);
                                }else if (marritalStr.equals("no")){
                                    marriedYes.setChecked(false);
                                    marriedNo.setChecked(true);
                                }else {
                                    marriedYes.setChecked(false);
                                    marriedNo.setChecked(false);
                                }


                                if (mcQ1 != null){
                                    txtque_one.setText(formlist.getMc_que_one()+"?");
                                    msustiontab1.setVisibility(View.VISIBLE);

                                }else {
                                    txtque_one.setVisibility(View.GONE);
                                    msustiontab1.setVisibility(View.GONE);
                                }
                                if (mcQ2 != null){
                                    txtque_two.setText(formlist.getMc_que_two()+"?");
                                    msustiontab2.setVisibility(View.VISIBLE);

                                }else {
                                    txtque_two.setVisibility(View.GONE);
                                    msustiontab2.setVisibility(View.GONE);
                                }

                                if (mcQ3 != null){
                                    editque_three.setText(formlist.getMc_que_three()+"?");
                                    msustiontab3.setVisibility(View.VISIBLE);
                                }else {
                                    editque_three.setVisibility(View.GONE);
                                    msustiontab3.setVisibility(View.GONE);
                                }

                                if (mcQ4 != null){
                                    txtqus_four.setText(formlist.getMc_que_four()+"?");
                                    msustiontab4.setVisibility(View.VISIBLE);
                                }else {
                                    txtqus_four.setVisibility(View.GONE);
                                    msustiontab4.setVisibility(View.GONE);
                                }
                                 if (que_one !=null){
                                     mc_qus1.setText(formlist.getQue_one()+"?");
                                     mc_qus1.setVisibility(View.VISIBLE);
                                     editmc_qus1.setVisibility(View.VISIBLE);
                                     mc_qus1_tab1.setVisibility(View.VISIBLE);
                                     editmc_qus1_tab1.setVisibility(View.VISIBLE);
                                 }else {
                                     editmc_qus1.setVisibility(View.GONE);
                                     mc_qus1.setVisibility(View.GONE);
                                     mc_qus1_tab1.setVisibility(View.GONE);
                                     editmc_qus1_tab1.setVisibility(View.GONE);

                                 }if (que_two !=null){
                                    mc_qus2.setText(formlist.getQue_two()+"?");
                                    mc_qus2.setVisibility(View.VISIBLE);
                                    editmc_qus2.setVisibility(View.VISIBLE);

                                    mc_qus1_tab2.setVisibility(View.VISIBLE);
                                    editmc_qus1_tab2.setVisibility(View.VISIBLE);
                                }else {
                                    editmc_qus2.setVisibility(View.GONE);
                                    mc_qus2.setVisibility(View.GONE);
                                    mc_qus1_tab2.setVisibility(View.GONE);
                                    editmc_qus1_tab2.setVisibility(View.GONE);

                                }if (que_three !=null){
                                    mc_qus3.setText(formlist.getQue_three()+"?");
                                    mc_qus3.setVisibility(View.VISIBLE);
                                    editmc_qus3.setVisibility(View.VISIBLE);
                                    mc_qus1_tab3.setVisibility(View.VISIBLE);
                                    editmc_qus1_tab3.setVisibility(View.VISIBLE);
                                }else {
                                    editmc_qus3.setVisibility(View.GONE);
                                    mc_qus3.setVisibility(View.GONE);
                                    mc_qus1_tab3.setVisibility(View.GONE);
                                    editmc_qus1_tab3.setVisibility(View.GONE);

                                }if (que_four !=null){
                                    mc_qus4.setText(formlist.getQue_four()+"?");
                                    mc_qus4.setVisibility(View.VISIBLE);
                                    editmc_qus4.setVisibility(View.VISIBLE);
                                    mc_qus1_tab4.setVisibility(View.VISIBLE);
                                    editmc_qus1_tab4.setVisibility(View.VISIBLE);
                                }else {
                                    editmc_qus4.setVisibility(View.GONE);
                                    mc_qus4.setVisibility(View.GONE);
                                    mc_qus1_tab4.setVisibility(View.GONE);
                                    editmc_qus1_tab4.setVisibility(View.GONE);

                                }

                            }    catch (Exception e) {
                                e.getMessage();
                                imgprogressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
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



    private void PostFormDetail(String key,String tkey,String shibirid,String name,String address,String city,String state,String pincode,String contact_no,String dob,
                                String age,String marrital_status,
                                String occupation,String ans_one,String ans_two,String ans_three ,String ans_four,String mc_ans_one,String mc_ans_two,
                                String mc_ans_three,String mc_ans_four) {

        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
            imgprogressBar.setVisibility(View.VISIBLE);

            Call<ShibirFormlistResponse> call = ApiClientMain.getApiClient().getShibirjoin(key,tkey,shibirid,name,address,city,state,pincode,contact_no,dob,age,marrital_status,
                    occupation,ans_one,ans_two,ans_three,ans_four,mc_ans_one,mc_ans_two,mc_ans_three,mc_ans_four,id);
            call.enqueue(new Callback<ShibirFormlistResponse>() {
                @Override
                public void onResponse(Call<ShibirFormlistResponse> call, Response<ShibirFormlistResponse> response) {

                    if (response != null && response.body() != null) {
                        ShibirFormlistResponse userResponse = response.body();
                        Shibirformlist formlist = userResponse.getData();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                imgprogressBar.setVisibility(View.GONE);
                                cardView.setVisibility(View.GONE);
                                cardView1.setVisibility(View.VISIBLE);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        } else {
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



    private void getStatelist(String tkey, String key ,String country_id) {
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<EventResponse> call = ApiClientMain.getApiClient().getStatelist(tkey, key,country_id);
            call.enqueue(new Callback<EventResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response != null && response.body() != null) {
                        EventResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {


                                List<EventList> statelist = response.body().getData();
                                for (int i = 0; i < statelist.size(); i++){
                                    String leaveType = statelist.get(i).getName();
                                    String statelistidStr = statelist.get(i).getId();
                                    statelistid.add(statelistidStr);
                                    listSpinner.add(leaveType);
                                }
                                     listSpinner.add(0, "- SELECT STATE -");//"- SELECT STATE -"
                                     statelistid.add(0, "");//"- SELECT STATE -"
                                ArrayAdapter    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
                               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                               state_spinner.setAdapter(adapter);

                        }else if (userResponse.getStatus().equals("error")){
                            Toast.makeText(getActivity(), ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        else {
                           // Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
                      //  Loading.Visibility(getContext(), view, 2);

                    }
                }

                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {
                   // Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
           // Loading.Visibility(getContext(), view, 404);
        }
    }



    private void getCitylist(String tkey, String key ,String state_id) {
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            Call<EventResponse> call = ApiClientMain.getApiClient().getCitylist(tkey, key,state_id);
            call.enqueue(new Callback<EventResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if (response != null && response.body() != null) {
                        EventResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {


                            List<EventList> statelist = response.body().getData();
                            for (int i = 0; i < statelist.size(); i++){
                                String leaveType = statelist.get(i).getName();
                                String statelistidStr = statelist.get(i).getId();
                                citylistid.add(statelistidStr);
                                citySpinnerlsit.add(leaveType);
                            }
                          //  listSpinner.add(0, "- SELECT STATE -");//"- SELECT STATE -"
                            //statelistid.add(0, "");//"- SELECT STATE -"

//                            List<String> citySpinnerlsit = new ArrayList<>();
//                            List<String> citylistid = new ArrayList<>();

                            ArrayAdapter    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, citySpinnerlsit);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            city_spinner.setAdapter(adapter);



                        }else if (userResponse.getStatus().equals("error")){
                            Toast.makeText(getActivity(), ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        else {
                            // Loading.Visibility(getContext(), view, 2);

                        }
                    } else {
                        //  Loading.Visibility(getContext(), view, 2);

                    }
                }

                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {
                    // Loading.Visibility(getContext(), view, 500);
                }
            });
        } else {
            // Loading.Visibility(getContext(), view, 404);
        }
    }






}
