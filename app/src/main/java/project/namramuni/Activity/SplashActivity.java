package project.namramuni.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.splunk.mint.Mint;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.ApplicationData;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Constant.RuntimePermissionsActivity;
import project.namramuni.R;
import project.namramuni.UIDesign.Loading;
import project.namramuni.ViewModels.List.UserList;
import project.namramuni.ViewModels.Main.HomeListResponse;
import project.namramuni.ViewModels.Main.UserResponse;
import project.namramuni.preference.PreferenceConstant;
import project.namramuni.preference.PreferenceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends RuntimePermissionsActivity {
    private static final int REQUEST_PERMISSIONS = 20;
    PreferenceManager preferenceManager;
    private Timer timer;
    Button btnTry;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Mint.initAndStartSession(this.getApplication(), "436a13cb");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        btnTry = findViewById(R.id.btnTry);
        preferenceManager = new PreferenceManager(this);
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
            }
        });
        SplashActivity.super.requestAppPermissions(new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WAKE_LOCK,
        }, R.string.runtime_permissions_txt, REQUEST_PERMISSIONS);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
//        String id = preferenceManager.getPreferenceValues(PreferenceConstant.id);
//        if (!id.equals("")) {
            timmer();
//        } else {
//        }
    }

    public void timmer() {
        final long period = 100;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i < 10) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    i++;
                } else {
                    timer.cancel();
                    getLogin();
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        }, 0, period);
    }

    private void getLogin() {
        if (NetworkInfo.isNetworkAvailable(SplashActivity.this)) {
            String did= ApplicationData.Deviceid(SplashActivity.this);
            Log.i("imeiInfo",did);
            Call<UserResponse> call = ApiClientMain.getApiClient().getIMEI(did);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response != null && response.body() != null) {
                        UserResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            if (userResponse.getRegister() == true) {
                                preferenceManager.putPreferenceValues(PreferenceConstant.IMEI, did);
                                startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
//                              startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            } else {

                                UserList list = userResponse.getData();
                                preferenceManager.putPreferenceValues(PreferenceConstant.id, list.getId());
                                preferenceManager.putPreferenceValues(PreferenceConstant.fname, list.getFirst_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.CODE, list.getCountry_code());
                                preferenceManager.putPreferenceValues(PreferenceConstant.IMEI, did);
                                preferenceManager.putPreferenceValues(PreferenceConstant.lname, list.getLast_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.EMAIL_ID, list.getEmail());
                                preferenceManager.putPreferenceValues(PreferenceConstant.mobile, list.getContact_no());
                                preferenceManager.putPreferenceValues(PreferenceConstant.address, list.getAddress());
                                preferenceManager.putPreferenceValues(PreferenceConstant.city, list.getCity());
                                preferenceManager.putPreferenceValues(PreferenceConstant.state, list.getState());
                                preferenceManager.putPreferenceValues(PreferenceConstant.pincode, list.getPincode());
                                preferenceManager.putPreferenceValues(PreferenceConstant.country, list.getCountry());
                                preferenceManager.putPreferenceValues(PreferenceConstant.PROFILE_PIC, list.getProfile_image());
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                        } else {
                                btnTry.setVisibility(View.VISIBLE);
                                Toast.makeText(SplashActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                             btnTry.setVisibility(View.VISIBLE);
                             Toast.makeText(SplashActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    btnTry.setVisibility(View.VISIBLE);
                    Toast.makeText(SplashActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(SplashActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            btnTry.setVisibility(View.VISIBLE);
        }
    }
}
