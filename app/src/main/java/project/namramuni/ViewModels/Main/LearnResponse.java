package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.LearnHeader;
import project.namramuni.ViewModels.List.LearnList;


public class LearnResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

/*

    @SerializedName("book_types")
    @Expose
    @Nullable
    private List<LearnHeader> heading_divinity;
*/

    @SerializedName("books")
    @Expose
    @Nullable
    private List<LearnList> play_list;

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

   /* @Nullable
    public List<LearnHeader> getHeading_divinity() {
        return heading_divinity;
    }

    public void setHeading_divinity(@Nullable List<LearnHeader> heading_divinity) {
        this.heading_divinity = heading_divinity;
    }*/

    @Nullable
    public List<LearnList> getPlay_list() {
        return play_list;
    }

    public void setPlay_list(@Nullable List<LearnList> play_list) {
        this.play_list = play_list;
    }
}
