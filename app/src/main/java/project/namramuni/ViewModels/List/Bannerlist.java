package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class Bannerlist implements Parcelable {
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;

    @SerializedName("banner_link")
    @Expose
    @Nullable
    private String banner_link;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;

    @SerializedName("news_title")
    @Expose
    @Nullable
    private String news_title;


    @Nullable
    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(@Nullable String news_title) {
        this.news_title = news_title;
    }

    @SerializedName("subtitle")
    @Expose
    @Nullable
    private String subtitle;

    @SerializedName("banner_img")
    @Expose
    @Nullable
    private String banner_img;

    @SerializedName("image")
    @Expose
    @Nullable
    private String image;

    @SerializedName("view_at")
    @Expose
    @Nullable
    private String view_at;

    @SerializedName("descrp")
    @Expose
    @Nullable
    private String descrp;

    @SerializedName("is_active")
    @Expose
    @Nullable
    private String is_active;

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(@Nullable String subtitle) {
        this.subtitle = subtitle;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getBanner_link() {
        return banner_link;
    }

    public void setBanner_link(@Nullable String banner_link) {
        this.banner_link = banner_link;
    }

    @Nullable
    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(@Nullable String banner_img) {
        this.banner_img = banner_img;
    }

    @Nullable
    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(@Nullable String is_active) {
        this.is_active = is_active;
    }

    public static final Creator<Bannerlist> CREATOR = new Creator<Bannerlist>() {
        @Override
        public Bannerlist createFromParcel(Parcel in) {
            return new Bannerlist(in);
        }

        @Override
        public Bannerlist[] newArray(int size) {
            return new Bannerlist[size];
        }
    };

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    @Nullable
    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(@Nullable String descrp) {
        this.descrp = descrp;
    }

    @Nullable
    public String getView_at() {
        return view_at;
    }

    public void setView_at(@Nullable String view_at) {
        this.view_at = view_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(banner_img);
        dest.writeString(banner_link);
        dest.writeString(is_active);
        dest.writeString(subtitle);
        dest.writeString(image);
        dest.writeString(view_at);
        dest.writeString(descrp);
        dest.writeString(title);
        dest.writeString(news_title);
    }
    public Bannerlist(Parcel in) {
        id = in.readString();
        banner_img= in.readString();
        banner_link = in.readString();
        is_active = in.readString();
        subtitle = in.readString();
        view_at = in.readString();
        image = in.readString();
        descrp = in.readString();
        title = in.readString();
        news_title = in.readString();
    }
}
