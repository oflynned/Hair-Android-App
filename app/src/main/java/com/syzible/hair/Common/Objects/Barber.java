package com.syzible.hair.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Barber {
    private String forename, surname, igUsername;

    public Barber(JSONObject o) throws JSONException {
        this.forename = o.getString("forename");
        this.surname = o.getString("surname");
        this.igUsername = o.getString("ig_username");
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getIgUsername() {
        return igUsername;
    }
}
