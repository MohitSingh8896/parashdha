package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shibirformlist implements Parcelable {

    //lucy draw
    @SerializedName("luckydraw_id")
    @Expose
    @Nullable
    private String luckydraw_id;

    @SerializedName("event_id")
    @Expose
    @Nullable
    private String event_id;

    @SerializedName("event_title")
    @Expose
    @Nullable
    private String event_title;


    @SerializedName("lucky_number")
    @Expose
    @Nullable
    private String lucky_number;

    @Nullable
    public String getLuckydraw_id() {
        return luckydraw_id;
    }

    public void setLuckydraw_id(@Nullable String luckydraw_id) {
        this.luckydraw_id = luckydraw_id;
    }

    @Nullable
    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(@Nullable String event_id) {
        this.event_id = event_id;
    }

    @Nullable
    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(@Nullable String event_title) {
        this.event_title = event_title;
    }

    @Nullable
    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(@Nullable String lucky_number) {
        this.lucky_number = lucky_number;
    }

    //Shibirformlist

    @SerializedName("id")
    @Expose
    @Nullable
    private String id;



    @SerializedName("address")
    @Expose
    @Nullable
    private String address;
    @SerializedName("description")
    @Expose
    @Nullable
    private String description;

    @SerializedName("contact_no")
    @Expose
    @Nullable
    private String contact_no;

   @SerializedName("shibir_venue")
    @Expose
    @Nullable
    private String shibir_venue;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;
    @SerializedName("city")
    @Expose
    @Nullable
    private String city;
    @SerializedName("pincode")
    @Expose
    @Nullable
    private String pincode;

    @SerializedName("mc_que_one")
    @Expose
    @Nullable
    private String mc_que_one;
   @SerializedName("mc_que_two")
    @Expose
    @Nullable
    private String mc_que_two;
    @SerializedName("mc_que_three")
    @Expose
    @Nullable
    private String mc_que_three;
    @SerializedName("mc_que_four")
    @Expose
    @Nullable
    private String mc_que_four;


    @SerializedName("que_one")
    @Expose
    @Nullable
    private String que_one;

    @SerializedName("dob")
    @Expose
    @Nullable
    private String dob;

    @SerializedName("que_two")
    @Expose
    @Nullable
    private String que_two;

    @SerializedName("que_three")
    @Expose
    @Nullable
    private String que_three;

    @SerializedName("que_four")
    @Expose
    @Nullable
    private String que_four;

    @SerializedName("marrital_status")
    @Expose
    @Nullable
    private String marrital_status;

    @SerializedName("occupation")
    @Expose
    @Nullable
    private String occupation;

    @SerializedName("profile_image")
    @Expose
    @Nullable
    private String profile_image;

    @SerializedName("state")
    @Expose
    @Nullable
    private String state;

    @SerializedName("age")
    @Expose
    @Nullable
    private String age;


    @Nullable
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(@Nullable String occupation) {
        this.occupation = occupation;
    }

    @Nullable
    public String getAge() {
        return age;
    }

    public void setAge(@Nullable String age) {
        this.age = age;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public void setState(@Nullable String state) {
        this.state = state;
    }

    @Nullable
    public String getDob() {
        return dob;
    }

    public void setDob(@Nullable String dob) {
        this.dob = dob;
    }


    @Nullable
    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(@Nullable String profile_image) {
        this.profile_image = profile_image;
    }

    @Nullable
    public String getMarrital_status() {
        return marrital_status;
    }

    public void setMarrital_status(@Nullable String marrital_status) {
        this.marrital_status = marrital_status;
    }

    @Nullable
    public String getQue_one() {
        return que_one;
    }

    public void setQue_one(@Nullable String que_one) {
        this.que_one = que_one;
    }

    @Nullable
    public String getQue_two() {
        return que_two;
    }

    public void setQue_two(@Nullable String que_two) {
        this.que_two = que_two;
    }

    @Nullable
    public String getQue_three() {
        return que_three;
    }

    public void setQue_three(@Nullable String que_three) {
        this.que_three = que_three;
    }

    @Nullable
    public String getQue_four() {
        return que_four;
    }

    public void setQue_four(@Nullable String que_four) {
        this.que_four = que_four;
    }

    @Nullable
    public String getMc_que_one() {
        return mc_que_one;
    }

    public void setMc_que_one(@Nullable String mc_que_one) {
        this.mc_que_one = mc_que_one;
    }

    @Nullable
    public String getMc_que_two() {
        return mc_que_two;
    }

    public void setMc_que_two(@Nullable String mc_que_two) {
        this.mc_que_two = mc_que_two;
    }

    @Nullable
    public String getMc_que_three() {
        return mc_que_three;
    }

    public void setMc_que_three(@Nullable String mc_que_three) {
        this.mc_que_three = mc_que_three;
    }

    @Nullable
    public String getMc_que_four() {
        return mc_que_four;
    }

    public void setMc_que_four(@Nullable String mc_que_four) {
        this.mc_que_four = mc_que_four;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }


    @Nullable
    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(@Nullable String contact_no) {
        this.contact_no = contact_no;
    }

    @Nullable
    public String getPincode() {
        return pincode;
    }

    public void setPincode(@Nullable String pincode) {
        this.pincode = pincode;
    }

    @Nullable
    public String getShibir_venue() {
        return shibir_venue;
    }

    public void setShibir_venue(@Nullable String shibir_venue) {
        this.shibir_venue = shibir_venue;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }


    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }



    public static final Creator<Shibirformlist> CREATOR = new Creator<Shibirformlist>() {
        @Override
        public Shibirformlist createFromParcel(Parcel in) {
            return new Shibirformlist(in);
        }

        @Override
        public Shibirformlist[] newArray(int size) {
            return new Shibirformlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);

        dest.writeString(address);
        dest.writeString(shibir_venue);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(pincode);
        dest.writeString(contact_no);
        dest.writeString(description);
        dest.writeString(mc_que_four);
        dest.writeString(mc_que_three);
        dest.writeString(mc_que_two);
        dest.writeString(mc_que_one);

        dest.writeString(que_one);
        dest.writeString(que_two);
        dest.writeString(que_three);
        dest.writeString(que_four);
        dest.writeString(marrital_status);
        dest.writeString(profile_image);
        dest.writeString(dob);
        dest.writeString(state);
        dest.writeString(age);
        dest.writeString(occupation);

        dest.writeString(luckydraw_id);
        dest.writeString(event_id);
        dest.writeString(event_title);
        dest.writeString(lucky_number);


    }
    public Shibirformlist(Parcel in) {
        id = in.readString();
        address= in.readString();
        shibir_venue= in.readString();
        name= in.readString();
        city= in.readString();
        pincode= in.readString();
        contact_no= in.readString();
        description= in.readString();
        mc_que_four= in.readString();
        mc_que_three= in.readString();
        mc_que_two= in.readString();
        mc_que_one= in.readString();
        que_one= in.readString();
        que_two= in.readString();
        que_three= in.readString();
        que_four= in.readString();
        marrital_status= in.readString();
        profile_image= in.readString();
        dob= in.readString();
        state= in.readString();
        age= in.readString();
        occupation= in.readString();

        luckydraw_id= in.readString();
        event_id= in.readString();
        event_title= in.readString();
        lucky_number= in.readString();

    }
}
