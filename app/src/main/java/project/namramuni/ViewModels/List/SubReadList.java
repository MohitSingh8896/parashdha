package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class SubReadList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("file_type")
    @Expose
    @Nullable
    private String file_type;

    @SerializedName("author_id")
    @Expose
    @Nullable
    private String author_id;

    @SerializedName("file_title")
    @Expose
    @Nullable
    private String file_title;

    @SerializedName("file_img")
    @Expose
    @Nullable
    private String file_img;

    @SerializedName("file_sub_title")
    @Expose
    @Nullable
    private String file_sub_title;

    @SerializedName("file_url")
    @Expose
    @Nullable
    private String file_url;

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
    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(@Nullable String file_type) {
        this.file_type = file_type;
    }

    @Nullable
    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(@Nullable String author_id) {
        this.author_id = author_id;
    }

    @Nullable
    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(@Nullable String file_title) {
        this.file_title = file_title;
    }

    @Nullable
    public String getFile_img() {
        return file_img;
    }

    public void setFile_img(@Nullable String file_img) {
        this.file_img = file_img;
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
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<SubReadList> CREATOR = new Creator<SubReadList>() {
        @Override
        public SubReadList createFromParcel(Parcel in) {
            return new SubReadList(in);
        }

        @Override
        public SubReadList[] newArray(int size) {
            return new SubReadList[size];
        }
    };

    public SubReadList() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(file_type);
        dest.writeString(author_id);
        dest.writeString(file_title);
        dest.writeString(file_img);
        dest.writeString(file_sub_title);
        dest.writeString(file_url);
        dest.writeString(is_active);
    }
    public SubReadList(Parcel in) {
        id = in.readString();
        file_type= in.readString();
        author_id = in.readString();
        file_title = in.readString();
        file_img = in.readString();
        file_sub_title = in.readString();
        file_url = in.readString();
        is_active = in.readString();
    }
}
