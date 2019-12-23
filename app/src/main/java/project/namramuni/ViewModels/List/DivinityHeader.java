package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class DivinityHeader implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @SerializedName("icon_name")
    @Expose
    @Nullable
    private String icon_name;

    @SerializedName("page_type")
    @Expose
    @Nullable
    private String page_type;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<DivinityHeader> CREATOR = new Creator<DivinityHeader>() {
        @Override
        public DivinityHeader createFromParcel(Parcel in) {
            return new DivinityHeader(in);
        }

        @Override
        public DivinityHeader[] newArray(int size) {
            return new DivinityHeader[size];
        }
    };

    public DivinityHeader() {

    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(@Nullable String icon_name) {
        this.icon_name = icon_name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
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
        dest.writeString(icon_name);
        dest.writeString(name);
        dest.writeString(page_type);
        dest.writeString(is_active);
    }
    public DivinityHeader(Parcel in) {
        id = in.readString();
        icon_name= in.readString();
        name = in.readString();
        page_type = in.readString();
        is_active = in.readString();
    }
}
