package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.Readlist;
import project.namramuni.ViewModels.List.SubReadList;


public class GurudevReadlistResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("files")
    @Expose
    @Nullable
    private List<SubReadList> files;

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
    public List<SubReadList> getFiles() {
        return files;
    }

    public void setFiles(@Nullable List<SubReadList> files) {
        this.files = files;
    }
}
