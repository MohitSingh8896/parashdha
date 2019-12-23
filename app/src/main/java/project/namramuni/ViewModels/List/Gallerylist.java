package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Gallerylist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("title_name")
    @Expose
    @Nullable
    private String title_name;

    @SerializedName("main_image")
    @Expose
    @Nullable
    private String main_image;

    @SerializedName("sub_image")
    @Expose
    @Nullable
    private String sub_image;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<Gallerylist> CREATOR = new Creator<Gallerylist>() {
        @Override
        public Gallerylist createFromParcel(Parcel in) {
            return new Gallerylist(in);
        }

        @Override
        public Gallerylist[] newArray(int size) {
            return new Gallerylist[size];
        }
    };

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(@Nullable String title_name) {
        this.title_name = title_name;
    }

    @Nullable
    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(@Nullable String main_image) {
        this.main_image = main_image;
    }

    @Nullable
    public String getSub_image() {
        return sub_image;
    }

    public void setSub_image(@Nullable String sub_image) {
        this.sub_image = sub_image;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title_name);
        dest.writeString(main_image);
        dest.writeString(sub_image);
        dest.writeString(is_active);
    }
    public Gallerylist(Parcel in) {
        id = in.readString();
        main_image= in.readString();
        title_name = in.readString();
        sub_image = in.readString();
        is_active = in.readString();
    }
}
