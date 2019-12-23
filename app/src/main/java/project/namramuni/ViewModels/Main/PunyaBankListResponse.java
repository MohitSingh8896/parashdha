package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.DivinityHeader;
import project.namramuni.ViewModels.List.DivinityList;


public class PunyaBankListResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("divinity_details")
    @Expose
    @Nullable
    private List<DivinityList> play_list;

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
    public List<DivinityList> getPlay_list() {
        return play_list;
    }

    public void setPlay_list(@Nullable List<DivinityList> play_list) {
        this.play_list = play_list;
    }
}
