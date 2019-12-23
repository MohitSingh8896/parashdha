package project.namramuni.ViewModels.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.Nullable;

public class EventList implements Parcelable,Serializable {

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;





    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
    //lucy Draw


    @SerializedName("event_id")
    @Expose
    @Nullable
    private String event_id;




    @SerializedName("luckydraw_id")
    @Expose
    @Nullable
    private String luckydraw_id;

    @SerializedName("lucky_number")
    @Expose
    @Nullable
    private String lucky_number;


    @Nullable
    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(@Nullable String event_id) {
        this.event_id = event_id;
    }

    @Nullable
    public String getLuckydraw_id() {
        return luckydraw_id;
    }

    public void setLuckydraw_id(@Nullable String luckydraw_id) {
        this.luckydraw_id = luckydraw_id;
    }

    @Nullable
    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(@Nullable String lucky_number) {
        this.lucky_number = lucky_number;
    }

    //schedule
    @SerializedName("id")
    @Expose
    @Nullable
    private String id;




    @SerializedName("event_title")
    @Expose
    @Nullable
    private String event_title;

    @SerializedName("event_address")
    @Expose
    @Nullable
    private String event_address;

    @SerializedName("start_date")
    @Expose
    @Nullable
    private String start_date;

    @SerializedName("event_description")
    @Expose
    @Nullable
    private String event_description;

    @SerializedName("start_time")
    @Expose
    @Nullable
    private String start_time;

    @SerializedName("end_time")
    @Expose
    @Nullable
    private String end_time;

    @SerializedName("end_date")
    @Expose
    @Nullable
    private String end_date;



//    shibir

    @SerializedName("shibir_venue")
    @Expose
    @Nullable
    private String shibir_venue;

    @SerializedName("shibir_title")
    @Expose
    @Nullable
    private String shibir_title;

    @SerializedName("start_from")
    @Expose
    @Nullable
    private String start_from;

    @SerializedName("end_to")
    @Expose
    @Nullable
    private String end_to;

    @SerializedName("shibir_attend")
    @Expose
    @Nullable
    private String shibir_attend;

    @Nullable
    public String getShibir_attend() {
        return shibir_attend;
    }

    public void setShibir_attend(@Nullable String shibir_attend) {
        this.shibir_attend = shibir_attend;
    }

    @Nullable
    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(@Nullable String start_time) {
        this.start_time = start_time;
    }

    @Nullable
    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(@Nullable String end_time) {
        this.end_time = end_time;
    }

    @Nullable
    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(@Nullable String end_date) {
        this.end_date = end_date;
    }

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(@Nullable String event_title) {
        this.event_title = event_title;
    }

    @Nullable
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(@Nullable String start_date) {
        this.start_date = start_date;
    }



    public static final Creator<EventList> CREATOR = new Creator<EventList>() {
        @Override
        public EventList createFromParcel(Parcel in) {
            return new EventList(in);
        }

        @Override
        public EventList[] newArray(int size) {
            return new EventList[size];
        }
    };

    @Nullable
    public String getShibir_venue() {
        return shibir_venue;
    }

    public void setShibir_venue(@Nullable String shibir_venue) {
        this.shibir_venue = shibir_venue;
    }

    @Nullable
    public String getEnd_to() {
        return end_to;
    }

    public void setEnd_to(@Nullable String end_to) {
        this.end_to = end_to;
    }

    @Nullable
    public String getShibir_title() {
        return shibir_title;
    }

    public void setShibir_title(@Nullable String shibir_title) {
        this.shibir_title = shibir_title;
    }

    @Nullable
    public String getStart_from() {
        return start_from;
    }

    public void setStart_from(@Nullable String start_from) {
        this.start_from = start_from;
    }



    @Nullable
    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(@Nullable String event_address) {
        this.event_address = event_address;
    }

    @Nullable
    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(@Nullable String event_description) {
        this.event_description = event_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(event_title);
        dest.writeString(start_date);
        dest.writeString(shibir_venue);
        dest.writeString(event_address);
        dest.writeString(event_description);
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(shibir_title);
        dest.writeString(start_from);
        dest.writeString(end_date);

        dest.writeString(luckydraw_id);
        dest.writeString(event_id);
        dest.writeString(lucky_number);
        dest.writeString(name);
    }
    public EventList(Parcel in) {
        id = in.readString();
        event_title= in.readString();
        start_date = in.readString();
        shibir_venue = in.readString();
        event_address = in.readString();
        event_description = in.readString();
        shibir_title = in.readString();
        start_from = in.readString();
        start_time = in.readString();
        end_time = in.readString();
        end_date = in.readString();

        luckydraw_id = in.readString();
        event_id = in.readString();
        lucky_number = in.readString();
        name = in.readString();
    }
}
