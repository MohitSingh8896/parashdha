package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class UserList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("profile_image")
    @Expose
    @Nullable
    private String profile_image;


    @SerializedName("name")
    @Expose
    @Nullable
    private  String name;



    @SerializedName("imei")
    @Expose
    @Nullable
    private String imei;

    @SerializedName("first_name")
    @Expose
    @Nullable
    private String first_name;

    @SerializedName("last_name")
    @Expose
    @Nullable
    private String last_name;


    @SerializedName("email")
    @Expose
    @Nullable
    private String email;

    @SerializedName("mobile_no")
    @Expose
    @Nullable
    private String contact_no;

    @SerializedName("country_code")
    @Expose
    @Nullable
    private String country_code;

    @SerializedName("address")
    @Expose
    @Nullable
    private String address;

    @SerializedName("city")
    @Expose
    @Nullable
    private String city;

    @SerializedName("state")
    @Expose
    @Nullable
    private String state;

    @SerializedName("pincode")
    @Expose
    @Nullable
    private String pincode;

    @SerializedName("country")
    @Expose
    @Nullable
    private String country;


    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @SerializedName("shibir_venue")
    @Expose
    @Nullable
    private String shibir_venue;

    @Nullable
    public String getShibir_venue() {
        return shibir_venue;
    }

    public void setShibir_venue(@Nullable String shibir_venue) {
        this.shibir_venue = shibir_venue;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(@Nullable String profile_image) {
        this.profile_image = profile_image;
    }

    @Nullable
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@Nullable String first_name) {
        this.first_name = first_name;
    }

    @Nullable
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@Nullable String last_name) {
        this.last_name = last_name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(@Nullable String contact_no) {
        this.contact_no = contact_no;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public void setState(@Nullable String state) {
        this.state = state;
    }

    @Nullable
    public String getPincode() {
        return pincode;
    }

    public void setPincode(@Nullable String pincode) {
        this.pincode = pincode;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    @Nullable
    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(@Nullable String country_code) {
        this.country_code = country_code;
    }

    @Nullable
    public String getImei() {
        return imei;
    }

    public void setImei(@Nullable String imei) {
        this.imei = imei;
    }

    public static final Creator<UserList> CREATOR = new Creator<UserList>() {
        @Override
        public UserList createFromParcel(Parcel in) {
            return new UserList(in);
        }

        @Override
        public UserList[] newArray(int size) {
            return new UserList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(email);
        dest.writeString(contact_no);
        dest.writeString(country_code);
        dest.writeString(address);
        dest.writeString(imei);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(pincode);
        dest.writeString(country);
        dest.writeString(profile_image);

        dest.writeString(shibir_venue);
        dest.writeString(name);



    }
    public UserList(Parcel in) {
        id = in.readString();
        imei= in.readString();
        first_name= in.readString();
        last_name = in.readString();
        email = in.readString();
        contact_no = in.readString();
        country_code = in.readString();
        address= in.readString();
        city = in.readString();
        state = in.readString();
        pincode = in.readString();
        country= in.readString();
        profile_image= in.readString();
        shibir_venue= in.readString();
        name= in.readString();
    }
}
