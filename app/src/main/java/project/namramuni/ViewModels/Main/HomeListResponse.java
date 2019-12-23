package project.namramuni.ViewModels.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import project.namramuni.ViewModels.List.Bannerlist;
import project.namramuni.ViewModels.List.LiveDataList;
import project.namramuni.ViewModels.List.LiveStream;
import project.namramuni.ViewModels.List.Quotelist;
import project.namramuni.ViewModels.List.Scorelist;
import project.namramuni.ViewModels.List.Scorelist1;
import project.namramuni.ViewModels.List.VideoList;
import project.namramuni.ViewModels.List.PDFList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class HomeListResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("banner_data")
    @Expose
    @Nullable
    private List<Bannerlist> banner_data;

    @SerializedName("news_data")
    @Expose
    @Nullable
    private List<Bannerlist> news_data;

    @SerializedName("quote_data")
    @Expose
    @Nullable
    private List<Quotelist> quote_data;

    @SerializedName("live_data")
    @Expose
    @Nullable
    private LiveDataList live_streaming;

    @SerializedName("video_list")
    @Expose
    @Nullable
    private List<VideoList> video_list;

    @SerializedName("audio_list")
    @Expose
    @Nullable
    private List<VideoList> audio_list;

    @SerializedName("bhakti_list")
    @Expose
    @Nullable
    private List<VideoList> bhakti_list;

    @SerializedName("publication")
    @Expose
    @Nullable
    private List<PDFList> publication;


    @SerializedName("group_score")
    @Expose
    @Nullable
    private List<Scorelist> group_score;


    @SerializedName("individual_score")
    @Expose
    @Nullable
    private List<Scorelist> individual_score;

    @Nullable
    public List<Scorelist> getIndividual_score() {
        return individual_score;
    }

    public void setIndividual_score(@Nullable List<Scorelist> individual_score) {
        this.individual_score = individual_score;
    }


    //    @SerializedName("individual_score")
//    @Expose
//    @Nullable
//    private List<Scorelist1> individual_score;
//
//
//    @Nullable
//    public List<Scorelist1> getIndividual_score() {
//        return individual_score;
//    }
//
//    public void setIndividual_score(@Nullable List<Scorelist1> individual_score) {
//        this.individual_score = individual_score;
//    }



    @Nullable
    public List<Scorelist> getGroup_score() {
        return group_score;
    }




    public void setGroup_score(@Nullable List<Scorelist> group_score) {
        this.group_score = group_score;
    }


    @Nullable
    public List<PDFList> getPublication() {
        return publication;
    }

    public void setPublication(@Nullable List<PDFList> publication) {
        this.publication = publication;
    }

    @Nullable
    public List<VideoList> getBhakti_list() {
        return bhakti_list;
    }

    public void setBhakti_list(@Nullable List<VideoList> bhakti_list) {
        this.bhakti_list = bhakti_list;
    }

    @Nullable
    public List<VideoList> getAudio_list() {
        return audio_list;
    }

    public void setAudio_list(@Nullable List<VideoList> audio_list) {
        this.audio_list = audio_list;
    }

    @Nullable
    public List<VideoList> getVideo_list() {
        return video_list;
    }

    public void setVideo_list(@Nullable List<VideoList> video_list) {
        this.video_list = video_list;
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
    public List<Bannerlist> getBanner_data() {
        return banner_data;
    }

    public void setBanner_data(@Nullable List<Bannerlist> banner_data) {
        this.banner_data = banner_data;
    }

    @Nullable
    public List<Quotelist> getQuote_data() {
        return quote_data;
    }

    public void setQuote_data(@Nullable List<Quotelist> quote_data) {
        this.quote_data = quote_data;
    }

    @Nullable
    public LiveDataList getLive_streaming() {
        return live_streaming;
    }

    public void setLive_streaming(@Nullable LiveDataList live_streaming) {
        this.live_streaming = live_streaming;
    }

    @Nullable
    public List<Bannerlist> getNews_data() {
        return news_data;
    }

    public void setNews_data(@Nullable List<Bannerlist> news_data) {
        this.news_data = news_data;
    }
}
