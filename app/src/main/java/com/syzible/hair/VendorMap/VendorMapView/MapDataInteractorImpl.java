package com.syzible.hair.VendorMap.VendorMapView;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.hair.Common.Network.RestClient;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class MapDataInteractorImpl implements MapDataInteractor {

    @Override
    public void fetch(String endpoint, final OnFetchFinished callback) {
        RestClient.get(endpoint, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                try {
                    callback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (OpeningTimeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                callback.onError(statusCode, rawJsonData);
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }
}
