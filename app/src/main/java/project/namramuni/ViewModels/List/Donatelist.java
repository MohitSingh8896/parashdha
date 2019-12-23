package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Donatelist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("donate_title")
    @Expose
    @Nullable
    private String donate_title;

    @SerializedName("donate_image")
    @Expose
    @Nullable
    private String donate_image;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @SerializedName("sub_image")
    @Expose
    @Nullable
    private String sub_image;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    @Nullable
    public String getSub_image() {
        return sub_image;
    }

    public void setSub_image(@Nullable String sub_image) {
        this.sub_image = sub_image;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getDonate_title() {
        return donate_title;
    }

    public void setDonate_title(@Nullable String donate_title) {
        this.donate_title = donate_title;
    }

    @Nullable
    public String getDonate_image() {
        return donate_image;
    }

    public void setDonate_image(@Nullable String donate_image) {
        this.donate_image = donate_image;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<Donatelist> CREATOR = new Creator<Donatelist>() {
        @Override
        public Donatelist createFromParcel(Parcel in) {
            return new Donatelist(in);
        }

        @Override
        public Donatelist[] newArray(int size) {
            return new Donatelist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(donate_title);
        dest.writeString(donate_image);
        dest.writeString(sub_image);
        dest.writeString(is_active);
    }
    public Donatelist(Parcel in) {
        id = in.readString();
        donate_title= in.readString();
        donate_image = in.readString();
        sub_image = in.readString();
        is_active = in.readString();
    }
}
