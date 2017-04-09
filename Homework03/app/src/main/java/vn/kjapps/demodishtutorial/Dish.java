package vn.kjapps.demodishtutorial;

import java.io.Serializable;

/**
 * Created by KJ Mok on 26/02/2017.
 */

public class Dish implements Serializable {
    private String name;
    private int image;
    private int high_image;
    private int image_tutorial;

    public Dish() {
    }

    public Dish(String name, int image, int high_image, int image_tutorial) {
        this.name = name;
        this.image = image;
        this.high_image = high_image;
        this.image_tutorial = image_tutorial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getHigh_image() {
        return high_image;
    }

    public void setHigh_image(int high_image) {
        this.high_image = high_image;
    }

    public int getImage_tutorial() {
        return image_tutorial;
    }

    public void setImage_tutorial(int image_tutorial) {
        this.image_tutorial = image_tutorial;
    }
}
