package project.namramuni.Pojo;

public class WatchPojo {
    String video,title,time;
    int img;
    public WatchPojo(String video, String title, String time) {
        this.video = video;
        this.title = title;
        this.time = time;
    }
    public WatchPojo(String title, String time,int img) {
        this.title = title;
        this.time = time;
        this.img = img;
    }
    public WatchPojo(String video, String title, String time,int img) {
        this.video = video;
        this.title = title;
        this.time = time;
        this.img = img;
    }

    public WatchPojo() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
