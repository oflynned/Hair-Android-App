package com.syzible.hair.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tags {

    private List<String> tags;

    public Tags(JSONArray a) throws JSONException {
        this.tags = grabTags(a);
    }

    public Tags(String... vendorTags) {
        tags = new ArrayList<>();
        tags.addAll(Arrays.asList(vendorTags));
    }

    private List<String> grabTags(JSONArray a) throws JSONException {
        tags = new ArrayList<>();
        for (int i = 0; i < a.length(); i++)
            tags.add(a.getString(i));

        return tags;
    }

    public String format() {
        StringBuilder itemTags = new StringBuilder();

        for (int i = 0; i < tags.size(); i++) {
            itemTags.append(tags.get(i));
            if (i < tags.size() - 1)
                itemTags.append(", ");
        }

        return itemTags.toString();
    }
}
