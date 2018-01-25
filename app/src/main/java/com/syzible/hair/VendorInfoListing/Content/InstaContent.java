package com.syzible.hair.VendorInfoListing.Content;

import com.syzible.hair.Common.Objects.Tags;

import org.json.JSONException;
import org.json.JSONObject;

public class InstaContent {
    private String igLink, caption, igUsername;

    private String standardQualityUrl, lowQualityUrl, thumbnailUrl;
    private int standardQualityWidth, standardQualityHeight;
    private int lowQualityWidth, lowQualityHeight;
    private int thumnailWidth, thumbnailHeight;

    private Tags tags;
    private long creationTime;

    public InstaContent(JSONObject o) throws JSONException {
        JSONObject imageData = o.getJSONObject("image_data");
        this.standardQualityUrl = imageData.getJSONObject("standard_resolution").getString("url");
        this.standardQualityWidth = imageData.getJSONObject("standard_resolution").getInt("width");
        this.standardQualityHeight = imageData.getJSONObject("standard_resolution").getInt("height");

        this.lowQualityUrl = imageData.getJSONObject("standard_resolution").getString("url");
        this.lowQualityWidth = imageData.getJSONObject("standard_resolution").getInt("width");
        this.lowQualityHeight = imageData.getJSONObject("standard_resolution").getInt("height");

        this.thumbnailUrl = imageData.getJSONObject("standard_resolution").getString("url");
        this.thumnailWidth = imageData.getJSONObject("standard_resolution").getInt("width");
        this.thumbnailHeight = imageData.getJSONObject("standard_resolution").getInt("height");

        this.tags = new Tags(o.getJSONArray("tags"));

        this.caption = o.getString("caption");
        this.creationTime = o.getLong("creation_time");
        this.igLink = o.getString("ig_photo_link");
        this.igUsername = o.getString("ig_username");
    }

    public String getStandardQualityUrl() {
        return standardQualityUrl;
    }

    public String getLowQualityUrl() {
        return lowQualityUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getStandardQualityWidth() {
        return standardQualityWidth;
    }

    public int getStandardQualityHeight() {
        return standardQualityHeight;
    }

    public int getLowQualityWidth() {
        return lowQualityWidth;
    }

    public int getLowQualityHeight() {
        return lowQualityHeight;
    }

    public int getThumnailWidth() {
        return thumnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public String getIgLink() {
        return igLink;
    }

    public String getCaption() {
        return caption;
    }

    public String getIgUsername() {
        return igUsername;
    }

    public Tags getTags() {
        return tags;
    }

    public long getCreationTime() {
        return creationTime;
    }
}
