package com.syzible.hair.Common.Objects;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class Vendor {
    private String vendorName, logoUrl, address;
    private double lat, lng;

    private Tags tags;
    private OpeningHours openingHours;
    private PriceList priceList;

    private boolean isPriority;

    public Vendor(JSONObject o) throws JSONException, OpeningTimeNotFoundException {
        this.vendorName = o.getString("vendor");
        this.openingHours = new OpeningHours(o.getJSONObject("opening_hours"));
        this.priceList = new PriceList(o.getJSONObject("prices"));

        JSONObject meta = o.getJSONObject("meta");
        this.logoUrl = meta.getString("logo");
        this.lat = meta.getJSONObject("location").getDouble("lat");
        this.lng = meta.getJSONObject("location").getDouble("lng");
        this.tags = new Tags(meta.getJSONArray("tags"));
    }

    public Vendor(String vendorName, String logoUrl, String address, double lat, double lng, Tags tags, OpeningHours openingHours, PriceList priceList, boolean isPriority) {
        this.vendorName = vendorName;
        this.logoUrl = logoUrl;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.tags = tags;
        this.openingHours = openingHours;
        this.priceList = priceList;
        this.isPriority = isPriority;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public LatLng getCoords() {
        return new LatLng(lat, lng);
    }

    public Tags getTags() {
        return tags;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public String getAddress() {
        return address;
    }

    public boolean isPriority() {
        return isPriority;
    }
}
