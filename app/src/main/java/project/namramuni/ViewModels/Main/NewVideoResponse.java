package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.EventList;
import project.namramuni.ViewModels.List.RecentVideoList;


public class NewVideoResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("record")
    @Expose
    @Nullable
    private List<RecentVideoList> record;

    @Nullable
    public List<RecentVideoList> getRecord() {
        return record;
    }

    public void setRecord(@Nullable List<RecentVideoList> record) {
        this.record = record;
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


}
