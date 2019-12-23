package project.namramuni.Activity;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.ApplicationData;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.R;
import project.namramuni.ViewModels.List.UserList;
import project.namramuni.ViewModels.Main.UserResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OTPActivity extends AppCompatActivity {
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.resend)
    TextView resend;
    @BindView(R.id.imgclose)
    ImageView imgclose;
    @BindView(R.id.t1)
    EditText t1;
    @BindView(R.id.t2)
    EditText t2;
    @BindView(R.id.t3)
    EditText t3;
    @BindView(R.id.t4)
    EditText t4;
    @BindView(R.id.t5)
    EditText t5;
    @BindView(R.id.t6)
    EditText t6;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
//        Toast.makeText(this, "" + getIntent().getStringExtra("otp"), Toast.LENGTH_LONG).show();
        preferenceManager = new PreferenceManager(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    getLogin();
                } else {
                    Toast.makeText(OTPActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
       /* String[] word = getIntent().getStringExtra("otp").split("");
        t1.setText(word[1]);
        t2.setText(word[2]);
        t3.setText(word[3]);
        t4.setText(word[4]);
        t5.setText(word[5]);
        t6.setText(word[6]);*/
        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t3.requestFocus();
                } else if (s.toString().length() == 0) {
                    t1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t4.requestFocus();
                } else if (s.toString().length() == 0) {
                    t2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t5.requestFocus();
                } else if (s.toString().length() == 0) {
                    t3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t6.requestFocus();
                } else if (s.toString().length() == 0) {
                    t4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 1) {
                    t6.clearFocus();
                } else if (s.toString().length() == 0) {
                    t5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResend();
            }
        });
    }

    private void getResend() {
        if (NetworkInfo.isNetworkAvailable(this)) {
            Call<UserResponse> call = ApiClientMain.getApiClient().getLogin(getIntent().getStringExtra("code"), getIntent().getStringExtra("mobile"));
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response != null && response.body() != null) {
                        UserResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            String[] word = userResponse.getCurrent_otp().split("");
                            t1.setText(word[1]);
                            t2.setText(word[2]);
                            t3.setText(word[3]);
                            t4.setText(word[4]);
                            t5.setText(word[5]);
                            t6.setText(word[6]);
                        } else {
                            Toast.makeText(OTPActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(OTPActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(OTPActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(OTPActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validation() {
        String b1 = t1.getText().toString().trim();
        String b2 = t2.getText().toString().trim();
        String b3 = t3.getText().toString().trim();
        String b4 = t4.getText().toString().trim();
        String b5 = t5.getText().toString().trim();
        String b6 = t6.getText().toString().trim();
        if (b1.equals("")) {
            return false;
        } else if (b2.equals("")) {
            return false;
        } else if (b3.equals("")) {
            return false;
        } else if (b4.equals("")) {
            return false;
        } else if (b5.equals("")) {
            return false;
        } else if (b5.equals("")) {
            return false;
        } else if (getIntent().getStringExtra("code").equals("")) {
            return false;
        } else if (getIntent().getStringExtra("mobile").equals("")) {
            return false;
        }
        return true;
    }

    private void getLogin() {
        if (NetworkInfo.isNetworkAvailable(OTPActivity.this)) {
            Call<UserResponse> call = ApiClientMain.getApiClient().getOTP(t1.getText().toString().trim()
                            + t2.getText().toString().trim() + t3.getText().toString().trim() + t4.getText().toString().trim()
                            + t5.getText().toString().trim() + t6.getText().toString().trim(),getIntent().getStringExtra("code"),
                            getIntent().getStringExtra("mobile"), ApplicationData.Deviceid(this), "register");
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response != null && response.body() != null) {
                        UserResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            UserList list = userResponse.getData();
                            if (userResponse.getUserStatus().equals("new_user")) {
                                preferenceManager.putPreferenceValues(PreferenceConstant.mobile, getIntent().getStringExtra("mobile"));
                                preferenceManager.putPreferenceValues(PreferenceConstant.CODE,getIntent().getStringExtra("code"));
//                                preferenceManager.putPreferenceValues(PreferenceConstant.IMEI, list.getImei());
                                startActivity(new Intent(OTPActivity.this, RegisterActivity.class));
                                finishAffinity();
                            } else {
                                preferenceManager.putPreferenceValues(PreferenceConstant.id, list.getId());
                                preferenceManager.putPreferenceValues(PreferenceConstant.fname, list.getFirst_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.lname, list.getLast_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.EMAIL_ID, list.getEmail());
                                preferenceManager.putPreferenceValues(PreferenceConstant.mobile, list.getContact_no());
                                preferenceManager.putPreferenceValues(PreferenceConstant.CODE, list.getCountry_code());
                                preferenceManager.putPreferenceValues(PreferenceConstant.address, list.getAddress());
                                preferenceManager.putPreferenceValues(PreferenceConstant.IMEI, list.getImei());
                                preferenceManager.putPreferenceValues(PreferenceConstant.city, list.getCity());
                                preferenceManager.putPreferenceValues(PreferenceConstant.state, list.getState());
                                preferenceManager.putPreferenceValues(PreferenceConstant.pincode, list.getPincode());
                                preferenceManager.putPreferenceValues(PreferenceConstant.country, list.getCountry());
                                preferenceManager.putPreferenceValues(PreferenceConstant.PROFILE_PIC, list.getProfile_image());
                                startActivity(new Intent(OTPActivity.this, MainActivity.class));
                                finish();
                            }
                        } else {
                            Toast.makeText(OTPActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(OTPActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(OTPActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(OTPActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
