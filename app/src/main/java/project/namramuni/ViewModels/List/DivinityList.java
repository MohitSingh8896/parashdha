package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class DivinityList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("type_name")
    @Expose
    @Nullable
    private String type_name;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;

    @SerializedName("category_name")
    @Expose
    @Nullable
    private String category_name;

    @SerializedName("link")
    @Expose
    @Nullable
    private String link;

    @SerializedName("playlist_image")
    @Expose
    @Nullable
    private String playlist_image;

    @SerializedName("playlist_url")
    @Expose
    @Nullable
    private String playlist_url;

    @SerializedName("cover_image")
    @Expose
    @Nullable
    private String cover_image;

    @SerializedName("playlist_name")
    @Expose
    @Nullable
    private String playlist_name;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<DivinityList> CREATOR = new Creator<DivinityList>() {
        @Override
        public DivinityList createFromParcel(Parcel in) {
            return new DivinityList(in);
        }

        @Override
        public DivinityList[] newArray(int size) {
            return new DivinityList[size];
        }
    };

    public DivinityList() {

    }

    @Nullable
    public String getPlaylist_url() {
        return playlist_url;
    }

    public void setPlaylist_url(@Nullable String playlist_url) {
        this.playlist_url = playlist_url;
    }

    @Nullable
    public String getType_name() {
        return type_name;
    }

    public void setType_name(@Nullable String type_name) {
        this.type_name = type_name;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(@Nullable String category_name) {
        this.category_name = category_name;
    }

    @Nullable
    public String getLink() {
        return link;
    }

    public void setLink(@Nullable String link) {
        this.link = link;
    }

    @Nullable
    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(@Nullable String cover_image) {
        this.cover_image = cover_image;
    }

    @Nullable
    public String getPlaylist_image() {
        return playlist_image;
    }

    public void setPlaylist_image(@Nullable String playlist_image) {
        this.playlist_image = playlist_image;
    }

    @Nullable
    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(@Nullable String playlist_name) {
        this.playlist_name = playlist_name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
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
        dest.writeString(playlist_image);
        dest.writeString(type_name);
        dest.writeString(playlist_name);
        dest.writeString(playlist_url);
        dest.writeString(cover_image);
        dest.writeString(category_name);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(is_active);
    }
    public DivinityList(Parcel in) {
        id = in.readString();
        playlist_image= in.readString();
        type_name = in.readString();
        playlist_name = in.readString();
        cover_image = in.readString();
        title = in.readString();
        category_name = in.readString();
        link = in.readString();
        playlist_url = in.readString();
        is_active = in.readString();
    }
}
