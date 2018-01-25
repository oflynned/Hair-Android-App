package com.syzible.hair.VendorList;

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

public class VendorListInteractorImpl implements VendorListInteractor {

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

    @Override
    public List<Vendor> fetchMockData() throws JSONException, OpeningTimeNotFoundException {
        List<Vendor> vendors = new ArrayList<>();

        vendors.add(new Vendor("GlassByte Barbers",
                "http://www.glassbyte.com/images/logo.png",
                "Phibsboro, Dublin 7",
                0,
                0,
                new Tags("Crew Cut"),
                new OpeningHours(),
                new PriceList(),
                true));

        vendors.add(new Vendor("Grafton Barber",
                "https://image.flaticon.com/icons/png/512/40/40861.png",
                "Grafton St, Dublin 2",
                0,
                0,
                new Tags("Luxury", "Trendy"),
                new OpeningHours(),
                new PriceList(),
                false));

        vendors.add(new Vendor("Fades and Blades",
                "https://upload.wikimedia.org/wikipedia/en/b/b2/Running_with_Scissors_logo.png",
                "Dorset St, Dublin 1",
                0,
                0,
                new Tags("Quick", "Stylish"),
                new OpeningHours(),
                new PriceList(),
                false));


        return vendors;
    }
}
