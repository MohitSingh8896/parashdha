package project.namramuni.Pojo;


public class ReadPojo {
    String title,subtitle,uthor;
    int img;

    public ReadPojo(String title, String subtitle, String uthor) {
        this.title = title;
        this.subtitle = subtitle;
        this.uthor = uthor;
    }

    public ReadPojo(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public ReadPojo(String title, String subtitle, String uthor, int img) {
        this.title = title;
        this.subtitle = subtitle;
        this.uthor = uthor;
        this.img = img;
    }
    public ReadPojo(String title, String subtitle,int img) {
        this.title = title;
        this.subtitle = subtitle;
        this.img = img;
    }

    public ReadPojo(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUthor() {
        return uthor;
    }

    public void setUthor(String uthor) {
        this.uthor = uthor;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
