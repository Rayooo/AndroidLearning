package com.ray.lab4;

/**
 * Created by Ray on 2016/11/3.
 */
public class Scenery {
    private String title;
    private String content;
    private int imageResource;

    public Scenery(String title, String content, int imageResource) {
        this.title = title;
        this.content = content;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return imageResource;
    }

    public void setImage(int imageResource) {
        this.imageResource = imageResource;
    }
}
