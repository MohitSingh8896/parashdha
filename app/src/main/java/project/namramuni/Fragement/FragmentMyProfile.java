package project.namramuni.Fragement;

import android.annotation.TargetApi;
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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.namramuni.Activity.MainActivity;
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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FragmentMyProfile extends Fragment {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgprogressBar)
    ProgressBar imgprogressBar;
    @BindView(R.id.profImage)
    ImageView profImage;
    @BindView(R.id.edtLName)
    EditText edtLName;
    @BindView(R.id.edtFName)
    EditText edtFName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtCity)
    EditText edtCity;
    @BindView(R.id.edtState)
    EditText edtState;
    @BindView(R.id.edtPincode)
    EditText edtPincode;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.edtcode)
    TextView edtcode;
    @BindView(R.id.btnTitle)
    Button btnTitle;
    @BindView(R.id.edtCountry)
    CountryCodePicker edtCountry;
    Boolean enabledisableStatus = false;
    private static final int REQUEST_CAMERA = 102;
    private static final int REQUEST_GALLARY = 103;
    final String FILE_ENTENSION = ".jpg";
    public String IMAGE_FILE_PATH;
    Uri imgUri;
    File f;
    String tempImageName = "", image;
    Item prescriptionImage = null;
    private File filePro;
    Bitmap rotatedBitmap = null;
    Boolean btnStatus = false;
    PreferenceManager preferenceManager1, preferenceManager;
    ProgressDialog hud;
    @BindView(R.id.edtCountryName)
    EditText edtCountryName;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myprofile, null);
        ((MainActivity) getActivity()).enableNavigationIcon();
        ButterKnife.bind(this, view);
        preferenceManager1 = new PreferenceManager(getContext(), "");
        preferenceManager = new PreferenceManager(getContext());
        storePath();
        getEditEnable(true);
        profImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enabledisableStatus == true) {
                    selectImage();
                } else {

//                    img = preferenceManager.getPreferenceValues(PreferenceConstant.PROFILE_PIC);
//                    if (img==null){
//                        startActivity(new Intent(getContext(), SingleFullScreenImage.class).putExtra("img", ""));
//                    } else {
//                        startActivity(new Intent(getContext(), SingleFullScreenImage.class).putExtra("img", img));
//                    }

                }
            }
        });
        printCoutnries();
        edit.setText("UPDATE");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkInfo.isNetworkAvailable(getActivity())) {
                    if (validation()) {
                        new LoadData().execute();
                    }
                } else {
                    Toast.makeText(getActivity(), "Check your internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData();
            }
        });
        getData();
        return view;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        if (NetworkInfo.isNetworkAvailable(getContext())) {
            String id = preferenceManager1.getPreferenceValues(PreferenceConstant.id);
            Call<UserResponse> call = ApiClientMain.getApiClient().getProfile(id);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response != null && response.body() != null) {
                        UserResponse userResponse = response.body();
                        UserList user = userResponse.getData();
                        if (userResponse.getStatus().equals("success")) {
                            try {
                                preferenceManager.putPreferenceValues(PreferenceConstant.fname, user.getFirst_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.lname, user.getLast_name());
                                preferenceManager.putPreferenceValues(PreferenceConstant.EMAIL_ID, user.getEmail());
                                preferenceManager.putPreferenceValues(PreferenceConstant.mobile, user.getContact_no());
                                preferenceManager.putPreferenceValues(PreferenceConstant.pincode, user.getPincode());
                                preferenceManager.putPreferenceValues(PreferenceConstant.address, user.getAddress());
                                preferenceManager.putPreferenceValues(PreferenceConstant.country, user.getCountry_code());
                                preferenceManager.putPreferenceValues(PreferenceConstant.city, user.getCity());
                                preferenceManager.putPreferenceValues(PreferenceConstant.state, user.getState());
                                preferenceManager.putPreferenceValues(PreferenceConstant.PROFILE_PIC, user.getProfile_image());

                                edtFName.setText(user.getFirst_name());
                                edtLName.setText(user.getLast_name());
                                edtEmail.setText(user.getEmail());
                                edtMobile.setText(user.getContact_no());
                                edtAddress.setText(user.getAddress());
                                edtCity.setText(user.getCity());
                                edtState.setText(user.getState());
                                edtPincode.setText(user.getPincode());
                                edtcode.setText(user.getCountry_code());
                                edtCountryName.setText(user.getCountry());

                                if (user.getProfile_image() != null) {
                                    profImage.setVisibility(View.GONE);
                                    imgprogressBar.setVisibility(View.VISIBLE);
                                    Picasso.with(getContext()).load(user.getProfile_image()).placeholder(R.drawable.profile).error(R.drawable.profile).into(profImage, new com.squareup.picasso.Callback() {
                                        @Override
                                        public void onSuccess() {
                                            imgprogressBar.setVisibility(View.GONE);
                                            profImage.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onError() {
                                            profImage.setVisibility(View.GONE);
                                            imgprogressBar.setVisibility(View.VISIBLE);
                                        }
                                    });
                                } else {
                                    imgprogressBar.setVisibility(View.GONE);
                                    profImage.setVisibility(View.VISIBLE);
                                    Picasso.with(getContext()).load(R.drawable.profile).placeholder(R.drawable.profile).error(R.drawable.profile).into(profImage);
                                }
                            } catch (Exception e) {
                                e.getMessage();
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(getActivity(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            Toast.makeText(getContext(), "Check your internet", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    class LoadData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            /*hud.show();*/
            hud = new ProgressDialog(getActivity());
            hud.setTitle("Please wait");
            hud.setCancelable(false);
            hud.show();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                result = uploadFile();
            } catch (Exception e) {
                e.printStackTrace();
                result = "afASF";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String code;
            try {
                JSONObject jsonObject = new JSONObject(result);
                code = jsonObject.getString("status");
                if (code.equals("success")) {
                    scheduleDismiss();
                    getData();
                } else {
                    scheduleDismiss();
                    Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException j) {
                j.printStackTrace();
                scheduleDismiss();
            }
        }
    }

    public void scheduleDismiss() {
        hud.dismiss();
    }

    private String uploadFile() {
        String responseStr = "", URl = "";
        try {
            URl = ApplicationData.update_user;
            FileUploader multipart = new FileUploader(URl, "UTF-8", getContext());
            String id = preferenceManager1.getPreferenceValues(PreferenceConstant.id);
            multipart.addFormField("user_id", id);
            multipart.addFormField("first_name", edtFName.getText().toString().trim());
            multipart.addFormField("last_name", edtLName.getText().toString().trim());
            multipart.addFormField("email", edtEmail.getText().toString().trim());
            multipart.addFormField("contact_no", edtMobile.getText().toString().trim());
            multipart.addFormField("address", edtAddress.getText().toString().trim());
            multipart.addFormField("city", edtCity.getText().toString().trim());
            multipart.addFormField("state", edtState.getText().toString().trim());
            multipart.addFormField("pincode", edtPincode.getText().toString().trim());
            multipart.addFormField("country_code", edtcode.getText().toString().trim());
            multipart.addFormField("country", edtCountryName.getText().toString().trim());
            if (filePro == null)
                multipart.addFilePart("profile_image", saveBitmapToFile(filePro));
            List<String> response = multipart.finish();
            responseStr = response.get(0);
        } catch (IOException error) {
            error.printStackTrace();
            scheduleDismiss();
        }
        return responseStr;
    }

    public File saveBitmapToFile(File file) {
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 100;
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 98, outputStream);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void printCoutnries() {
        edtCountry.setHidePhoneCode(true);
        edtcode.setText("+91");
        edtCountry.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                edtcode.setText("+" + selectedCountry.getPhoneCode());
                edtCountryName.setText(selectedCountry.getName());
            }
        });
        edtCountryName.setText(edtCountry.getDefaultCountryName());
    }

    private boolean validation() {
        String pincode = edtPincode.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String fname = edtFName.getText().toString().trim();
        String lname = edtLName.getText().toString().trim();
        String code = edtcode.getText().toString().trim();
        String mob = edtMobile.getText().toString().trim();
        String state = edtState.getText().toString().trim();
        if (fname.equals("")) {
            edtFName.requestFocus();
            edtFName.setError("Enter Field");
            return false;
        } else if (lname.equals("")) {
            edtLName.requestFocus();
            edtLName.setError("Enter Field");
            return false;
        } else if (email.equals("")) {
            edtEmail.requestFocus();
            edtEmail.setError("Enter Field");
            return false;
        } else if (mob.equals("")) {
            edtMobile.requestFocus();
            edtMobile.setError("Enter Field");
            return false;
        } else if (address.equals("")) {
            edtAddress.requestFocus();
            edtAddress.setError("Enter Field");
            return false;
        } else if (edtCity.equals("")) {
            edtCity.requestFocus();
            edtCity.setError("Enter Field");
            return false;
        } else if (city.equals("")) {
            edtCity.requestFocus();
            edtCity.setError("Enter Field");
            return false;
        } else if (state.equals("")) {
            edtState.requestFocus();
            edtState.setError("Enter Field");
            return false;
        } else if (pincode.equals("")) {
            edtPincode.requestFocus();
            edtPincode.setError("Enter Field");
            return false;
        } else if (code.equals("")) {
            edtcode.requestFocus();
            edtcode.setError("Enter Field");
            return false;
        }
        return true;
    }

    private void getEditEnable(Boolean truefals) {
        edtFName.setEnabled(truefals);
        edtLName.setEnabled(truefals);
        edtEmail.setEnabled(truefals);
        edtMobile.setEnabled(truefals);
        edtPincode.setEnabled(truefals);
        edtAddress.setEnabled(truefals);
        edtState.setEnabled(truefals);
        edtCity.setEnabled(truefals);
        profImage.setEnabled(truefals);
        enabledisableStatus = truefals;
        if (truefals == true) {
            edtFName.setTextColor(getResources().getColor(R.color.black));
            edtLName.setTextColor(getResources().getColor(R.color.black));
            edtEmail.setTextColor(getResources().getColor(R.color.black));
            edtMobile.setTextColor(getResources().getColor(R.color.black));
            edtAddress.setTextColor(getResources().getColor(R.color.black));
            edtPincode.setTextColor(getResources().getColor(R.color.black));
            edtState.setTextColor(getResources().getColor(R.color.black));
            edtCity.setTextColor(getResources().getColor(R.color.black));
        } else {
            edtFName.setTextColor(getResources().getColor(R.color.gray1));
            edtLName.setTextColor(getResources().getColor(R.color.gray1));
            edtEmail.setTextColor(getResources().getColor(R.color.gray1));
            edtMobile.setTextColor(getResources().getColor(R.color.gray1));
            edtAddress.setTextColor(getResources().getColor(R.color.gray1));
            edtState.setTextColor(getResources().getColor(R.color.gray1));
            edtPincode.setTextColor(getResources().getColor(R.color.gray1));
            edtCity.setTextColor(getResources().getColor(R.color.gray1));
        }
    }

    private void storePath() {
        tempImageName = "";
        IMAGE_FILE_PATH = Environment.getExternalStorageDirectory() + "/ImageFolder";
        File directory = new File(Environment.getExternalStorageDirectory() + "/ImageFolder");
        if (directory.exists()) {
            File[] contents = directory.listFiles();
            if (contents != null) {
                if (contents.length > 0) {
                    for (int i = 0; i < contents.length; i++) {
                        if (contents[i].isFile()) {
                            File sourceFile = new File(contents[i].toString());
                            sourceFile.delete();
                        }
                    }
                }
            }
        } else {
            directory.mkdir();
        }
    }

    private void selectImage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_image_picker, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width = GridLayoutManager.LayoutParams.WRAP_CONTENT;
        alertDialog.getWindow().setAttributes(lp);
        RelativeLayout rltCam = (RelativeLayout) dialogView.findViewById(R.id.rltCam);
        RelativeLayout rltGallery = (RelativeLayout) dialogView.findViewById(R.id.rltGallery);
        rltCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                imgUri = openCameraIntent();

            }
        });
        rltGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                openGallery();

            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public void openGallery() {
        Intent captureIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        captureIntent.setType("image/*");
        if (captureIntent.resolveActivity(getContext().getApplicationContext().getPackageManager()) != null) {
            startActivityForResult(captureIntent, REQUEST_GALLARY);
        } else {
            Toast.makeText(getContext(), "Unable to open gallary", Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURI() {
        String result;
        Cursor cursor = getContext().getApplicationContext().getContentResolver().query(imgUri, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = imgUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Uri getImageUri(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String createFileName() {
        return "" + System.currentTimeMillis();
    }

    public String getFileExtension() {
        return FILE_ENTENSION;
    }

    public Uri openCameraIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        tempImageName = createFileName() + getFileExtension();
        File photo = new File(IMAGE_FILE_PATH, tempImageName);
        String a = getContext().getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri photoURI = FileProvider.getUriForFile(getActivity(), a + ".provider", photo);
            intent.putExtra("output", photoURI);
        } else {
            intent.putExtra("output", Uri.fromFile(photo));
        }
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA);
        } else {
            Toast.makeText(getContext(), "Unable to open camera", Toast.LENGTH_LONG).show();
        }
        return Uri.fromFile(photo);
    }

    public Item getCapturedImage(int requestCode, boolean makeImageGrey) {
        Item myClass = null;
        String picturePath = null;
        if (requestCode == REQUEST_CAMERA) {
            try {
                myClass = new Item();
                File f = new File(IMAGE_FILE_PATH + "/" + tempImageName);
                String imageName = f.getName();
                if (imageName == null) {
                    return null;
                }
                picturePath = f.getAbsolutePath();

                if (f.exists()) {
                } else {
                    picturePath = "";
                }

                myClass.setPath(picturePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_GALLARY) {
            try {
                String selectedImagePath = getRealPathFromURI();
                File tempfile = new File(selectedImagePath);
                String MovedFilePath = copyFile(selectedImagePath, tempfile.getName(), IMAGE_FILE_PATH + "/");
                myClass = new Item();
                myClass.setPath(MovedFilePath);
                myClass.setTitle(tempfile.getName());

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return myClass;
    }

    private String copyFile(String inputPath, String inputFile, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
        return outputPath + inputFile;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public Bitmap getBitmap(String path) {
        Bitmap bitmap = null;
        try {

            File f = new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            // image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bitmap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            if (requestCode == REQUEST_CAMERA) {
                if (resultCode == RESULT_OK) {
                    prescriptionImage = getCapturedImage(requestCode, true);
                    if (prescriptionImage.getPath().length() > 0) {
                        f = new File(prescriptionImage.getPath());
                        filePro = f;
                        Bitmap bitmap = getBitmap(prescriptionImage.getPath());
                        ExifInterface ei = new ExifInterface(prescriptionImage.getPath());
                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                rotatedBitmap = rotateImage(bitmap, 90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                rotatedBitmap = rotateImage(bitmap, 180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                rotatedBitmap = rotateImage(bitmap, 270);
                                break;
                            case ExifInterface.ORIENTATION_NORMAL:
                            default:
                                rotatedBitmap = bitmap;
                        }
                        Picasso.with(getContext()).load(getImageUri(rotatedBitmap)).error(R.drawable.logo).into(profImage);
                    } else {
                        Toast.makeText(getContext(), "No image Captured.", Toast.LENGTH_SHORT).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    Toast.makeText(getContext(),
                            "No image was selected", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // failed to capture image
                    Toast.makeText(getContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }
            } else if (requestCode == REQUEST_GALLARY) {
                if (intent == null) {
                    Toast.makeText(getContext(), "No image selected.", Toast.LENGTH_SHORT).show();
                } else {
                    imgUri = intent.getData();
                    prescriptionImage = getCapturedImage(requestCode, true);
                    if (prescriptionImage.getPath().length() > 0) {
                        f = new File(prescriptionImage.getPath());
                        filePro = f;
                        Bitmap bitmap = getBitmap(prescriptionImage.getPath());
                        ExifInterface ei = new ExifInterface(prescriptionImage.getPath());
                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                rotatedBitmap = rotateImage(bitmap, 90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                rotatedBitmap = rotateImage(bitmap, 180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                rotatedBitmap = rotateImage(bitmap, 270);
                                break;
                            case ExifInterface.ORIENTATION_NORMAL:
                            default:
                                rotatedBitmap = bitmap;
                        }
                        Picasso.with(getContext()).load(getImageUri(rotatedBitmap)).error(R.drawable.logo).into(profImage);
                    } else {
                        Toast.makeText(getContext(), "No image Captured.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
