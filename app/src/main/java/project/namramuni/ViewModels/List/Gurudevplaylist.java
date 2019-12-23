package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gurudevplaylist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;


    public static final Creator<Gurudevplaylist> CREATOR = new Creator<Gurudevplaylist>() {
        @Override
        public Gurudevplaylist createFromParcel(Parcel in) {
            return new Gurudevplaylist(in);
        }

        @Override
        public Gurudevplaylist[] newArray(int size) {
            return new Gurudevplaylist[size];
        }
    };


    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }




    public Gurudevplaylist() {

    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);


    }
    public Gurudevplaylist(Parcel in) {
        id = in.readString();
        title= in.readString();


    }
}
