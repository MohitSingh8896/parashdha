package project.namramuni.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Constant.ApiClientMain;
import project.namramuni.Constant.ApplicationData;
import project.namramuni.Constant.FileUploader;
import project.namramuni.Constant.NetworkInfo;
import project.namramuni.Pojo.Item;
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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.imgclose)
    ImageView imgclose;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtCountry)
    CountryCodePicker edtCountry;
    @BindView(R.id.edtcode)
    TextView edtcode;
    ProgressDialog hud;
    PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitilogin);
        ButterKnife.bind(this);
        hud = new ProgressDialog(this);
        preferenceManager = new PreferenceManager(this);
        printCoutnries();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    getLogin();
                }
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void printCoutnries() {
        edtCountry.setHidePhoneCode(true);
        edtcode.setText("+" + edtCountry.getDefaultCountryCode());
        edtCountry.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                edtcode.setText("+" + selectedCountry.getPhoneCode());
            }
        });
    }

    private boolean validation() {
        String code = edtcode.getText().toString().trim();
        String mob = edtMobile.getText().toString().trim();
        if (mob.equals("")) {
            edtMobile.requestFocus();
            edtMobile.setError("Enter Field");
            return false;
        } else if (code.equals("")) {
            edtcode.requestFocus();
            edtcode.setError("Enter Field");
            return false;
        }
        return true;
    }

    private void getLogin() {
        if (NetworkInfo.isNetworkAvailable(LoginActivity.this)) {
            Call<UserResponse> call = ApiClientMain.getApiClient().getLogin(edtcode.getText().toString().trim(), edtMobile.getText().toString().trim());
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response != null && response.body() != null) {
                        UserResponse userResponse = response.body();
                        if (userResponse.getStatus().equals("success")) {
                            startActivity(new Intent(LoginActivity.this, OTPActivity.class)
                                    .putExtra("otp", userResponse.getCurrent_otp())
                                    .putExtra("code", edtcode.getText().toString().trim())
                                    .putExtra("mobile", edtMobile.getText().toString().trim()));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void scheduleDismiss() {
        hud.dismiss();
    }
}
