package com.syzible.hair.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpeningHours {
    private List<OpeningTime> openingTimes;

    public OpeningHours(JSONObject o) throws JSONException, OpeningTimeNotFoundException {
        openingTimes = new ArrayList<>();
        Iterator<?> keys = o.keys();

        while (keys.hasNext()) {
            String day = (String) keys.next();
            String openingTime = o.getJSONObject(day).getString("opening");
            String closingTime = o.getJSONObject(day).getString("closing");

            openingTimes.add(new OpeningTime(day, openingTime, closingTime));
        }
    }

    public OpeningHours() throws OpeningTimeNotFoundException {
        openingTimes = new ArrayList<>();
        openingTimes.add(new OpeningTime("monday", "9:00", "18:00"));
        openingTimes.add(new OpeningTime("tuesday", "9:00", "18:00"));
        openingTimes.add(new OpeningTime("wednesday", "9:00", "19:00"));
        openingTimes.add(new OpeningTime("thursday", "9:00", "19:00"));
        openingTimes.add(new OpeningTime("friday", "9:00", "20:00"));
        openingTimes.add(new OpeningTime("saturday", "9:00", "20:00"));
        openingTimes.add(new OpeningTime("sunday", "9:00", "18:00"));
    }

    public List<OpeningTime> getOpeningTimes() {
        return openingTimes;
    }
}
