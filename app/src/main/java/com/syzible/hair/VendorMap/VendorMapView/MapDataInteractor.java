package com.syzible.hair.VendorMap.VendorMapView;

import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

public interface MapDataInteractor {

    void fetch(String endpoint, OnFetchFinished onFetchFinished);

    interface OnFetchFinished {
        void onError(int statusCode, String message);

        void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException;
    }
}
