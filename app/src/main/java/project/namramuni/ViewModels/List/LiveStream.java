package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class LiveStream implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("live_set_image")
    @Expose
    @Nullable
    private String live_set_image;

    @SerializedName("live_video_title")
    @Expose
    @Nullable
    private String live_video_title;

    @SerializedName("full_url")
    @Expose
    @Nullable
    private String full_url;

    @SerializedName("short_utl")
    @Expose
    @Nullable
    private String short_utl;

    @SerializedName("live_timeing")
    @Expose
    @Nullable
    private String live_timeing;

    @SerializedName("contact_no")
    @Expose
    @Nullable
    private String contact_no;

    @SerializedName("about_us")
    @Expose
    @Nullable
    private String about_us;

    @SerializedName("address")
    @Expose
    @Nullable
    private String address;

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getLive_set_image() {
        return live_set_image;
    }

    public void setLive_set_image(@Nullable String live_set_image) {
        this.live_set_image = live_set_image;
    }

    @Nullable
    public String getLive_video_title() {
        return live_video_title;
    }

    public void setLive_video_title(@Nullable String live_video_title) {
        this.live_video_title = live_video_title;
    }

    @Nullable
    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(@Nullable String full_url) {
        this.full_url = full_url;
    }

    @Nullable
    public String getShort_utl() {
        return short_utl;
    }

    public void setShort_utl(@Nullable String short_utl) {
        this.short_utl = short_utl;
    }

    @Nullable
    public String getLive_timeing() {
        return live_timeing;
    }

    public void setLive_timeing(@Nullable String live_timeing) {
        this.live_timeing = live_timeing;
    }

    @Nullable
    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(@Nullable String contact_no) {
        this.contact_no = contact_no;
    }

    @Nullable
    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(@Nullable String about_us) {
        this.about_us = about_us;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public static final Creator<LiveStream> CREATOR = new Creator<LiveStream>() {
        @Override
        public LiveStream createFromParcel(Parcel in) {
            return new LiveStream(in);
        }

        @Override
        public LiveStream[] newArray(int size) {
            return new LiveStream[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(live_set_image);
        dest.writeString(live_video_title);
        dest.writeString(full_url);
        dest.writeString(short_utl);
        dest.writeString(live_timeing);
        dest.writeString(contact_no);
        dest.writeString(address);
        dest.writeString(about_us);
    }
    public LiveStream(Parcel in) {
        id= in.readString();
        live_set_image= in.readString();
        live_video_title= in.readString();
        full_url= in.readString();
        short_utl= in.readString();
        live_timeing= in.readString();
        contact_no= in.readString();
        address= in.readString();
        about_us = in.readString();
    }
}
