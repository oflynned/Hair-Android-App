package com.syzible.hair.Common.Interactors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface JsonInteractor {

    interface JsonObjectInteractor {
        void fetch(String endpoint, OnFetchFinished onFetchFinished);
    }

    interface JsonArrayInteractor {
        void fetch(String endpoint, OnFetchFinished onFetchFinished);
    }

    interface OnFetchFinished {
        void onError(int statusCode, String message);

        void onSuccess(JSONArray a) throws JSONException;

        void onSuccess(JSONObject o);
    }
}
