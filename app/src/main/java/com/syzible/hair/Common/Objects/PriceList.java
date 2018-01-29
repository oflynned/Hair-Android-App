package com.syzible.hair.Common.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriceList {
    private List<Price> prices;

    public PriceList(JSONObject o) throws JSONException {
        prices = new ArrayList<>();
        Iterator<String> keys = o.keys();

        while (keys.hasNext()) {
            String style = keys.next();
            String description = o.getJSONObject(style).getString("description");
            double cost = o.getJSONObject(style).getDouble("cost");
            prices.add(new Price(style, description, cost));
        }
    }

    public List<Price> getPrices() {
        return prices;
    }
}
