package com.syzible.hair.Common.Interactors;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.hair.Common.Network.RestClient;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JsonObjectInteractorImpl implements JsonInteractor.JsonObjectInteractor{
    @Override
    public void fetch(String endpoint, final JsonInteractor.OnFetchFinished callback) {
        RestClient.get(endpoint, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                callback.onError(statusCode, rawJsonData);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }
}
