package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class LearnList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("type_name")
    @Expose
    @Nullable
    private String type_name;

    @SerializedName("file_img")
    @Expose
    @Nullable
    private String file_img;

    @SerializedName("file_title")
    @Expose
    @Nullable
    private String file_title;

    @SerializedName("file_sub_title")
    @Expose
    @Nullable
    private String file_sub_title;

    @SerializedName("file_url")
    @Expose
    @Nullable
    private String file_url;

    @SerializedName("file_id")
    @Expose
    @Nullable
    private String file_id;


    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<LearnList> CREATOR = new Creator<LearnList>() {
        @Override
        public LearnList createFromParcel(Parcel in) {
            return new LearnList(in);
        }

        @Override
        public LearnList[] newArray(int size) {
            return new LearnList[size];
        }
    };

    public LearnList() { }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getType_name() {
        return type_name;
    }

    public void setType_name(@Nullable String type_name) {
        this.type_name = type_name;
    }

    @Nullable
    public String getFile_img() {
        return file_img;
    }

    public void setFile_img(@Nullable String file_img) {
        this.file_img = file_img;
    }

    @Nullable
    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(@Nullable String file_title) {
        this.file_title = file_title;
    }

    @Nullable
    public String getFile_sub_title() {
        return file_sub_title;
    }

    public void setFile_sub_title(@Nullable String file_sub_title) {
        this.file_sub_title = file_sub_title;
    }

    @Nullable
    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(@Nullable String file_url) {
        this.file_url = file_url;
    }

    @Nullable
    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(@Nullable String file_id) {
        this.file_id = file_id;
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
        dest.writeString(file_img);
        dest.writeString(type_name);
        dest.writeString(file_sub_title);
        dest.writeString(file_title);
        dest.writeString(file_id);
        dest.writeString(file_url);
        dest.writeString(is_active);
    }
    public LearnList(Parcel in) {
        id = in.readString();
        file_id= in.readString();
        file_img= in.readString();
        file_sub_title= in.readString();
        file_title= in.readString();
        file_url= in.readString();
        type_name = in.readString();
        is_active = in.readString();
    }
}
