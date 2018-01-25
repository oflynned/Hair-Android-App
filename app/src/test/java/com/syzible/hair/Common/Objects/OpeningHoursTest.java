package com.syzible.hair.Common.Objects;

import com.syzible.hair.VendorMap.VendorMapView.MapDataInteractor;
import com.syzible.hair.VendorMap.VendorMapView.MapDataInteractorImpl;
import com.syzible.hair.Common.Network.Endpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

public class OpeningHoursTest {

    @Test
    public void test_vendor_hours_correctly_parsed() throws JSONException {
        MapDataInteractor.OnFetchFinished onFetchFinished = new MapDataInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {

            }

            @Override
            public void onSuccess(JSONArray a) {
                System.out.println(a);
            }

        };

        MapDataInteractor interactor = new MapDataInteractorImpl();
        interactor.fetch(Endpoint.GET_ALL_VENDORS, onFetchFinished);
    }
}