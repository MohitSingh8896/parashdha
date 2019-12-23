package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Readlist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("file_name")
    @Expose
    @Nullable
    private String file_name;

    @SerializedName("file_icon")
    @Expose
    @Nullable
    private String file_icon;

    @SerializedName("page_type")
    @Expose
    @Nullable
    private String page_type;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<Readlist> CREATOR = new Creator<Readlist>() {
        @Override
        public Readlist createFromParcel(Parcel in) {
            return new Readlist(in);
        }

        @Override
        public Readlist[] newArray(int size) {
            return new Readlist[size];
        }
    };

    public Readlist() {

    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(@Nullable String file_name) {
        this.file_name = file_name;
    }

    @Nullable
    public String getFile_icon() {
        return file_icon;
    }

    public void setFile_icon(@Nullable String file_icon) {
        this.file_icon = file_icon;
    }

    @Nullable
    public String getPage_type() {
        return page_type;
    }

    public void setPage_type(@Nullable String page_type) {
        this.page_type = page_type;
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
        dest.writeString(file_icon);
        dest.writeString(file_name);
        dest.writeString(page_type);
        dest.writeString(is_active);
    }
    public Readlist(Parcel in) {
        id = in.readString();
        file_icon= in.readString();
        file_name = in.readString();
        page_type = in.readString();
        is_active = in.readString();
    }
}
