package com.syzible.hair.VendorInfoListing.Content;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.hair.Common.Network.RestClient;
import com.syzible.hair.Common.Objects.OpeningHours;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.PriceList;
import com.syzible.hair.Common.Objects.Tags;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ContentInteractorImpl implements ContentInteractor {

    @Override
    public void fetch(String endpoint, final OnFetchFinished onFetchFinished) {
        RestClient.get(endpoint, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                try {
                    onFetchFinished.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (OpeningTimeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                onFetchFinished.onError(statusCode, rawJsonData);
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }
}
