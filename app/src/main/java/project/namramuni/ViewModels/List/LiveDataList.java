package project.namramuni.ViewModels.List;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveDataList implements Parcelable {
    @SerializedName("message")
    @Expose
    @Nullable
    private String message;

    @SerializedName("url")
    @Expose
    @Nullable
    private String url;

    @SerializedName("isLive")
    @Expose
    @Nullable
    private Boolean isLive;

    public static final Creator<LiveDataList> CREATOR = new Creator<LiveDataList>() {
        @Override
        public LiveDataList createFromParcel(Parcel in) {
            return new LiveDataList(in);
        }

        @Override
        public LiveDataList[] newArray(int size) {
            return new LiveDataList[size];
        }
    };

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(@Nullable Boolean isLive) {
        this.isLive = isLive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(isLive);
        dest.writeString(message);
        dest.writeString(url);
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public LiveDataList(Parcel in) {
        isLive = in.readBoolean();
        message= in.readString();
        url = in.readString();
    }
}
