package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scorelist1 implements Parcelable {

    @SerializedName("total_points")
    @Expose
    @Nullable
    private String total_points;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @Nullable
    public String getTotal_points() {
        return total_points;
    }

    public void setTotal_points(@Nullable String total_points) {
        this.total_points = total_points;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public static final Creator<Scorelist1> CREATOR = new Creator<Scorelist1>() {
        @Override
        public Scorelist1 createFromParcel(Parcel in) {
            return new Scorelist1(in);
        }

        @Override
        public Scorelist1[] newArray(int size) {
            return new Scorelist1[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(total_points);

    }
    public Scorelist1(Parcel in) {
        name = in.readString();
        total_points = in.readString();

    }
}
