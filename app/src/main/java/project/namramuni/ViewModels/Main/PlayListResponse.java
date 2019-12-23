package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.List.PlayList;


public class PlayListResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("songlist")
    @Expose
    @Nullable
    private List<PlayList> data;

    @SerializedName("playlist")
    @Expose
    @Nullable
    private List<Gurudevlist> playlist;

    @SerializedName("data")
    @Expose
    @Nullable
    private List<PlayList> datas;

    @Nullable
    public List<PlayList> getDatas() {
        return datas;
    }

    public void setDatas(@Nullable List<PlayList> datas) {
        this.datas = datas;
    }

    @Nullable
    public List<Gurudevlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(@Nullable List<Gurudevlist> playlist) {
        this.playlist = playlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Nullable
    public List<PlayList> getData() {
        return data;
    }

    public void setData(@Nullable List<PlayList> data) {
        this.data = data;
    }
}
