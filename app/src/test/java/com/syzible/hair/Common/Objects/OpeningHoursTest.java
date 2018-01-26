package com.syzible.hair.Common.Objects;

import com.syzible.hair.Common.Interactors.DataInteractor;
import com.syzible.hair.Common.Interactors.DataInteractorImpl;
import com.syzible.hair.Common.Network.Endpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

public class OpeningHoursTest {

    @Test
    public void test_vendor_hours_correctly_parsed() throws JSONException {
        DataInteractor.OnFetchFinished onFetchFinished = new DataInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {

            }

            @Override
            public void onSuccess(JSONArray a) {
                System.out.println(a);
            }

        };

        DataInteractor interactor = new DataInteractorImpl();
        interactor.fetch(Endpoint.GET_ALL_VENDORS, onFetchFinished);
    }
}