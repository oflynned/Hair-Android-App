package com.syzible.hair.Common.Objects;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Price {

    private String style;
    private double cost;

    public Price(String style, double cost) {
        this.style = style;
        this.cost = cost;
    }

    public String getStyle() {
        return style;
    }

    public double getCost() {
        return cost;
    }

    public String getFormattedCost() {
        NumberFormat irishFormat = NumberFormat.getInstance(Locale.UK);
        return "€" + irishFormat.format(cost);
    }
}
