package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class ActivityBoxList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("acti_title")
    @Expose
    @Nullable
    private String acti_title;

    @SerializedName("acti_image")
    @Expose
    @Nullable
    private String acti_image;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getActi_title() {
        return acti_title;
    }

    public void setActi_title(@Nullable String acti_title) {
        this.acti_title = acti_title;
    }

    @Nullable
    public String getActi_image() {
        return acti_image;
    }

    public void setActi_image(@Nullable String acti_image) {
        this.acti_image = acti_image;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<ActivityBoxList> CREATOR = new Creator<ActivityBoxList>() {
        @Override
        public ActivityBoxList createFromParcel(Parcel in) {
            return new ActivityBoxList(in);
        }

        @Override
        public ActivityBoxList[] newArray(int size) {
            return new ActivityBoxList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(is_active);
        dest.writeString(acti_image);
        dest.writeString(acti_title);
    }
    public ActivityBoxList(Parcel in) {
        id = in.readString();
        acti_title = in.readString();
        acti_image = in.readString();
        is_active = in.readString();
    }
}
