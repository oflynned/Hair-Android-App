package com.syzible.hair.Common.Objects;

import java.text.NumberFormat;
import java.util.Locale;

public class Price {
    private String style, description;
    private double cost;

    public Price(String style, String description, double cost) {
        this.style = style;
        this.description = description;
        this.cost = cost;
    }

    public String getStyle() {
        return style;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedCost() {
        NumberFormat irishFormat = NumberFormat.getInstance(Locale.UK);
        return "€" + irishFormat.format(cost);
    }
}
