package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ActivityBoxContent {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("heading_title")
    @Expose
    private String heading_title;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("lyrics_text")
    @Expose
    private String lyrics_text;

    @SerializedName("is_complete")
    @Expose
    private String is_complete;

    @SerializedName("audio_link")
    @Expose
    private String audio_link;

    public String getHeading_title() {
        return heading_title;
    }

    public void setHeading_title(String heading_title) {
        this.heading_title = heading_title;
    }

    public String getLyrics_text() {
        return lyrics_text;
    }

    public void setLyrics_text(String lyrics_text) {
        this.lyrics_text = lyrics_text;
    }

    public String getAudio_link() {
        return audio_link;
    }

    public void setAudio_link(String audio_link) {
        this.audio_link = audio_link;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(String is_complete) {
        this.is_complete = is_complete;
    }
}
