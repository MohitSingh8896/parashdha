package project.namramuni.Pojo;

/**
 * Created by admin on 7/26/2018.
 */

public class MenuDataModel {

    String name;
    int image;

    public MenuDataModel(String name, int image) {
        this.name = name;
        this.image=image;
    }

    public String getName() {
        return name;
    }



    public int getImage() {
        return image;
    }

}
