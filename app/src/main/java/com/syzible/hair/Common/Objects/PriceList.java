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
        Iterator<?> keys = o.keys();

        while (keys.hasNext()) {
            String style = (String) keys.next();
            double cost = o.getDouble(style);
            prices.add(new Price(style, cost));
        }
    }

    public List<Price> getPrices() {
        return prices;
    }
}
