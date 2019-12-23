package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Quotelist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("quote_title")
    @Expose
    @Nullable
    private String quote_title;

    @SerializedName("quote_image")
    @Expose
    @Nullable
    private String quote_image;

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
    public String getDonate_title() {
        return quote_title;
    }

    public void setDonate_title(@Nullable String quote_title) {
        this.quote_title = quote_title;
    }

    @Nullable
    public String getDonate_image() {
        return quote_image;
    }

    public void setDonate_image(@Nullable String quote_image) {
        this.quote_image = quote_image;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<Quotelist> CREATOR = new Creator<Quotelist>() {
        @Override
        public Quotelist createFromParcel(Parcel in) {
            return new Quotelist(in);
        }

        @Override
        public Quotelist[] newArray(int size) {
            return new Quotelist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(quote_title);
        dest.writeString(quote_image);
        dest.writeString(is_active);
    }
    public Quotelist(Parcel in) {
        id = in.readString();
        quote_title= in.readString();
        quote_image = in.readString();
        is_active = in.readString();
    }
}
