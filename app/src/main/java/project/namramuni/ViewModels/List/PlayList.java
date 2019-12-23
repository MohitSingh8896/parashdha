package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class PlayList implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("type_name")
    @Expose
    @Nullable
    private String type_name;

    @SerializedName("p_list_id")
    @Expose
    @Nullable
    private String p_list_id;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;

    @SerializedName("cover_image")
    @Expose
    @Nullable
    private String cover_image;

    @SerializedName("list")
    @Expose
    @Nullable
    private String list;

    @SerializedName("a_id")
    @Expose
    @Nullable
    private String a_id;

    @SerializedName("song_name")
    @Expose
    @Nullable
    private String song_name;

    @SerializedName("song_url")
    @Expose
    @Nullable
    private String song_url;

    @SerializedName("song_duration")
    @Expose
    @Nullable
    private String song_duration;

    @SerializedName("upload_time")
    @Expose
    @Nullable
    private String upload_time;

    @SerializedName("playlist_dtl")
    @Expose
    @Nullable
    private VideoList playlist_dtl;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    public static final Creator<PlayList> CREATOR = new Creator<PlayList>() {
        @Override
        public PlayList createFromParcel(Parcel in) {
            return new PlayList(in);
        }

        @Override
        public PlayList[] newArray(int size) {
            return new PlayList[size];
        }
    };

    public PlayList() {

    }

    @Nullable
    public VideoList getPlaylist_dtl() {
        return playlist_dtl;
    }

    public void setPlaylist_dtl(@Nullable VideoList playlist_dtl) {
        this.playlist_dtl = playlist_dtl;
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

    @Nullable
    public String getType_name() {
        return type_name;
    }

    public void setType_name(@Nullable String type_name) {
        this.type_name = type_name;
    }

    @Nullable
    public String getP_list_id() {
        return p_list_id;
    }

    public void setP_list_id(@Nullable String p_list_id) {
        this.p_list_id = p_list_id;
    }

    @Nullable
    public String getA_id() {
        return a_id;
    }

    public void setA_id(@Nullable String a_id) {
        this.a_id = a_id;
    }

    @Nullable
    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(@Nullable String song_name) {
        this.song_name = song_name;
    }

    @Nullable
    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(@Nullable String song_url) {
        this.song_url = song_url;
    }

    @Nullable
    public String getSong_duration() {
        return song_duration;
    }

    public void setSong_duration(@Nullable String song_duration) {
        this.song_duration = song_duration;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(@Nullable String cover_image) {
        this.cover_image = cover_image;
    }

    @Nullable
    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(@Nullable String upload_time) {
        this.upload_time = upload_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type_name);
        dest.writeString(a_id);
        dest.writeString(song_name);
        dest.writeString(song_duration);
        dest.writeString(song_url);
        dest.writeString(upload_time);
        dest.writeString(is_active);
        dest.writeString(list);
        dest.writeString(p_list_id);
        dest.writeString(cover_image);
        dest.writeString(title);
        dest.writeParcelable(playlist_dtl,flags);
    }

    @Nullable
    public String getList() {
        return list;
    }

    public void setList(@Nullable String list) {
        this.list = list;
    }

    public PlayList(Parcel in) {
        id = in.readString();
        type_name = in.readString();
        list = in.readString();
        a_id = in.readString();
        song_name = in.readString();
        song_duration = in.readString();
        song_url = in.readString();
        upload_time = in.readString();
        type_name = in.readString();
        is_active = in.readString();
        p_list_id = in.readString();
        title = in.readString();
        cover_image = in.readString();
        playlist_dtl = in.readParcelable(PlayList.class.getClassLoader());
    }
}
