package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scorelist implements Parcelable {

    @SerializedName("total_points")
    @Expose
    @Nullable
    private String total_points;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

   @SerializedName("rank")
    @Expose
    @Nullable
    private String rank;

    @Nullable
    public String getRank() {
        return rank;
    }

    public void setRank(@Nullable String rank) {
        this.rank = rank;
    }

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

    public static final Creator<Scorelist> CREATOR = new Creator<Scorelist>() {
        @Override
        public Scorelist createFromParcel(Parcel in) {
            return new Scorelist(in);
        }

        @Override
        public Scorelist[] newArray(int size) {
            return new Scorelist[size];
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
        dest.writeString(rank);

    }
    public Scorelist(Parcel in) {
        name = in.readString();
        total_points = in.readString();
        rank = in.readString();

    }
}
