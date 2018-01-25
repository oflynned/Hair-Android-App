package com.syzible.hair.Common.Objects;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class Barbers {
    private List<Barber> barbers;

    public Barbers(JSONArray a) throws JSONException {
        for (int i = 0; i < a.length(); i++)
            barbers.add(new Barber(a.getJSONObject(i)));
    }

    public List<Barber> getBarbers() {
        return barbers;
    }
}
