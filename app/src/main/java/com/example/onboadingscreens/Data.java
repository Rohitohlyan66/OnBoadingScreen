package com.example.onboadingscreens;

public class Data {

    String title;
    String description;
    int images;

    public Data(String title, String description, int images) {
        this.title = title;
        this.description = description;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
