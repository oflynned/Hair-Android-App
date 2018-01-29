package com.syzible.hair.VendorList;

import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface VendorListInteractor {

    void fetch(String endpoint, OnFetchFinished onFetchFinished);

    interface OnFetchFinished {
        void onError(int statusCode, String message);

        void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException;
    }
}
