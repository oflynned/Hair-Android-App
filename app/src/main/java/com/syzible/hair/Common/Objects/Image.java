package com.syzible.hair.Common.Objects;

public class Image {
    private String imageUrl;
    private int width;
    private int height;

    public Image(String imageUrl, int width, int height) {
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
