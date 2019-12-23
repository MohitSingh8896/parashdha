package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Gurudevlist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("genre_id")
    @Expose
    @Nullable
    private String genre_id;

    @SerializedName("type_name")
    @Expose
    @Nullable
    private String type_name;

    @SerializedName("playlist_image")
    @Expose
    @Nullable
    private String playlist_image;

    @SerializedName("playlist_name")
    @Expose
    @Nullable
    private String playlist_name;

    @SerializedName("playlist_url")
    @Expose
    @Nullable
    private String playlist_url;

    @SerializedName("create_date")
    @Expose
    @Nullable
    private String create_date;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    @SerializedName("total")
    @Expose
    @Nullable
    private String total;

    @SerializedName("view_at")
    @Expose
    @Nullable
    private String view_at;

    public static final Creator<Gurudevlist> CREATOR = new Creator<Gurudevlist>() {
        @Override
        public Gurudevlist createFromParcel(Parcel in) {
            return new Gurudevlist(in);
        }

        @Override
        public Gurudevlist[] newArray(int size) {
            return new Gurudevlist[size];
        }
    };

    public Gurudevlist() {

    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(@Nullable String genre_id) {
        this.genre_id = genre_id;
    }

    @Nullable
    public String getType_name() {
        return type_name;
    }

    public void setType_name(@Nullable String type_name) {
        this.type_name = type_name;
    }

    @Nullable
    public String getPlaylist_image() {
        return playlist_image;
    }

    public void setPlaylist_image(@Nullable String playlist_image) {
        this.playlist_image = playlist_image;
    }

    @Nullable
    public String getPlaylist_url() {
        return playlist_url;
    }

    public void setPlaylist_url(@Nullable String playlist_url) {
        this.playlist_url = playlist_url;
    }

    @Nullable
    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(@Nullable String playlist_name) {
        this.playlist_name = playlist_name;
    }

    @Nullable
    public String getTotal() {
        return total;
    }

    public void setTotal(@Nullable String total) {
        this.total = total;
    }

    @Nullable
    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(@Nullable String create_date) {
        this.create_date = create_date;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    @Nullable
    public String getView_at() {
        return view_at;
    }

    public void setView_at(@Nullable String view_at) {
        this.view_at = view_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(genre_id);
        dest.writeString(type_name);
        dest.writeString(playlist_url);
        dest.writeString(playlist_image);
        dest.writeString(view_at);
        dest.writeString(playlist_name);
        dest.writeString(create_date);
        dest.writeString(total);
        dest.writeString(is_active);
    }
    public Gurudevlist(Parcel in) {
        id = in.readString();
        genre_id= in.readString();
        type_name = in.readString();
        playlist_image = in.readString();
        playlist_url = in.readString();
        view_at = in.readString();
        playlist_name = in.readString();
        create_date = in.readString();
        total = in.readString();
        is_active = in.readString();
    }
}
