package com.syzible.hair.Common.Persistence;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.LatLng;

public class Prefs {
    public enum Pref {
        last_lat, last_lng
    }

    public static void setDoublePref(Context context, Pref pref, double value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putFloat(pref.name(), (float) value)
                .apply();
    }

    public static double getDoublePref(Context context, Pref pref) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getFloat(pref.name(), 0);
    }

    public static LatLng getLastLocation(Context context) {
        double lat = getDoublePref(context, Pref.last_lat);
        double lng = getDoublePref(context, Pref.last_lng);
        return new LatLng(lat, lng);
    }
}
