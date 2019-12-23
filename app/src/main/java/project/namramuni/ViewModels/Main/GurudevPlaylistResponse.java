package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.Gurudevlist;
import project.namramuni.ViewModels.List.Gurudevplaylist;


public class GurudevPlaylistResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("record")
    @Expose
    @Nullable
    private List<Gurudevplaylist> record;

    @SerializedName("items")
    @Expose
    @Nullable
    private List<Gurudevplaylist> items;


    @Nullable
    public List<Gurudevplaylist> getRecord() {
        return record;
    }

    public void setRecord(@Nullable List<Gurudevplaylist> record) {
        this.record = record;
    }

    @Nullable
    public List<Gurudevplaylist> getItems() {
        return items;
    }

    public void setItems(@Nullable List<Gurudevplaylist> items) {
        this.items = items;
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
