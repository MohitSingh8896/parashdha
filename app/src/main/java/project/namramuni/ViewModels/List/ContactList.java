package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class ContactList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("center_name")
    @Expose
    @Nullable
    private String center_name;

    @SerializedName("center_address")
    @Expose
    @Nullable
    private String center_address;

    @SerializedName("center_phone_no")
    @Expose
    @Nullable
    private String center_phone_no;

    @SerializedName("center_email")
    @Expose
    @Nullable
    private String center_email;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    @Nullable
    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(@Nullable String center_name) {
        this.center_name = center_name;
    }

    @Nullable
    public String getCenter_address() {
        return center_address;
    }

    public void setCenter_address(@Nullable String center_address) {
        this.center_address = center_address;
    }

    @Nullable
    public String getCenter_phone_no() {
        return center_phone_no;
    }

    public void setCenter_phone_no(@Nullable String center_phone_no) {
        this.center_phone_no = center_phone_no;
    }

    @Nullable
    public String getCenter_email() {
        return center_email;
    }

    public void setCenter_email(@Nullable String center_email) {
        this.center_email = center_email;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<ContactList> CREATOR = new Creator<ContactList>() {
        @Override
        public ContactList createFromParcel(Parcel in) {
            return new ContactList(in);
        }

        @Override
        public ContactList[] newArray(int size) {
            return new ContactList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(center_address);
        dest.writeString(center_email);
        dest.writeString(center_name);
        dest.writeString(center_phone_no);
        dest.writeString(is_active);
    }
    public ContactList(Parcel in) {
        id = in.readString();
        center_address= in.readString();
        center_email= in.readString();
        center_name= in.readString();
        center_phone_no = in.readString();
        is_active = in.readString();
    }
}
