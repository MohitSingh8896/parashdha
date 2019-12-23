package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import project.namramuni.ViewModels.List.Shibirformlist;
import project.namramuni.ViewModels.List.UserList;

public class ShibirFormlistResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    @Nullable
    private Shibirformlist data;

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
    public Shibirformlist getData() {
        return data;
    }

    public void setData(@Nullable Shibirformlist data) {
        this.data = data;
    }
}
