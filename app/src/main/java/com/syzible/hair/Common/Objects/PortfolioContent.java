package com.syzible.hair.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class PortfolioContent {
    private String caption, igUsername, igPhotoLink;
    private long creationTime;
    private Tags tags;
    private Image image;

    public PortfolioContent(JSONObject o) throws JSONException {
        this.caption = o.getString("caption");
        this.creationTime = o.getLong("creation_time");
        this.igUsername = o.getString("ig_username");
        this.igPhotoLink = o.getString("ig_photo_link");
        this.tags = new Tags(o.getJSONArray("tags"));

        JSONObject imageObject = o.getJSONObject("image_url");
        this.image = new Image(imageObject.getString("url"), imageObject.getInt("width"), imageObject.getInt("height"));
    }

    public String getCaption() {
        return caption;
    }

    public String getIgUsername() {
        return igUsername;
    }

    public String getIgPhotoLink() {
        return igPhotoLink;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public Tags getTags() {
        return tags;
    }

    public Image getImage() {
        return image;
    }
}
