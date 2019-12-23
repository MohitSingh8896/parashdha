package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class PDFList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("file_title")
    @Expose
    @Nullable
    private String file_title;

    @SerializedName("file_img")
    @Expose
    @Nullable
    private String file_img;

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
    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(@Nullable String file_title) {
        this.file_title = file_title;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    @Nullable
    public String getFile_img() {
        return file_img;
    }

    public void setFile_img(@Nullable String file_img) {
        this.file_img = file_img;
    }

    @Nullable
    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(@Nullable String file_url) {
        this.file_url = file_url;
    }

    public static final Creator<PDFList> CREATOR = new Creator<PDFList>() {
        @Override
        public PDFList createFromParcel(Parcel in) {
            return new PDFList(in);
        }

        @Override
        public PDFList[] newArray(int size) {
            return new PDFList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(file_title);
        dest.writeString(file_url);
        dest.writeString(file_img);
        dest.writeString(is_active);
    }
    public PDFList(Parcel in) {
        id = in.readString();
        file_title= in.readString();
        file_url= in.readString();
        file_img= in.readString();
        is_active = in.readString();
    }

}
