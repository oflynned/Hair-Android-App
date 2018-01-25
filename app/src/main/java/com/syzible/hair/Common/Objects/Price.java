package com.syzible.hair.Common.Objects;

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
}
