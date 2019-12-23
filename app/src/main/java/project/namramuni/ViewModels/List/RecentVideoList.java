package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecentVideoList implements Parcelable,Serializable {


    @SerializedName("file_title")
    @Expose
    @Nullable
    private String file_title;

    @SerializedName("description")
    @Expose
    @Nullable
    private String description;

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @SerializedName("videoId")
    @Expose
    @Nullable
    private String videoId;

    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("file_url")
    @Expose
    @Nullable
    private String file_url;

    @SerializedName("file_img")
    @Expose
    @Nullable
    private String file_img;

    @SerializedName("totalResults")
    @Expose
    @Nullable
    private String totalResults;

    @SerializedName("url")
    @Expose
    @Nullable
    private String url;

    @SerializedName("publishedAt")
    @Expose
    @Nullable
    private String publishedAt;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;

    @Nullable
    public String getTitle() {
        return title;
    }


    @Nullable
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(@Nullable String videoId) {
        this.videoId = videoId;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(@Nullable String totalResults) {
        this.totalResults = totalResults;
    }

    public static final Creator<RecentVideoList> CREATOR = new Creator<RecentVideoList>() {
        @Override
        public RecentVideoList createFromParcel(Parcel in) {
            return new RecentVideoList(in);
        }

        @Override
        public RecentVideoList[] newArray(int size) {
            return new RecentVideoList[size];
        }
    };

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
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(@Nullable String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(@Nullable String file_url) {
        this.file_url = file_url;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(file_title);
        dest.writeString(file_img);
        dest.writeString(publishedAt);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(totalResults);
        dest.writeString(file_url);
        dest.writeString(videoId);
        dest.writeString(description);

    }
    public RecentVideoList(Parcel in) {
        file_title = in.readString();
        file_img= in.readString();
        publishedAt= in.readString();
        title= in.readString();
        url= in.readString();
        id= in.readString();
        totalResults= in.readString();
        file_url= in.readString();
        videoId= in.readString();
        description= in.readString();

    }
}
