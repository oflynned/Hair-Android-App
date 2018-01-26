package com.syzible.hair.VendorInfoListing.Content;

import android.util.Log;

import com.syzible.hair.Common.Interactors.JsonArrayInteractorImpl;
import com.syzible.hair.Common.Interactors.JsonInteractor;
import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContentPresenterImpl implements ContentPresenter {
    private ContentView contentView;
    private JsonInteractor.JsonArrayInteractor interactor;

    @Override
    public void attach(ContentView contentView) {
        this.contentView = contentView;
        this.interactor = new JsonArrayInteractorImpl();
    }

    @Override
    public void detach() {
        this.contentView = null;
        this.interactor = null;
    }

    @Override
    public void start() {
        interactor.fetch(Endpoint.GET_CONTENT_STREAM, new JsonInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {
                Log.e(getClass().getSimpleName(), statusCode + ": " + message);
            }

            @Override
            public void onSuccess(JSONArray a) throws JSONException {
                List<InstaContent> content = new ArrayList<>();
                for (int i = 0; i < a.length(); i++)
                    content.add(new InstaContent(a.getJSONObject(i)));

                if (contentView != null) {
                    contentView.setAdapter(content);
                }
            }

            @Override
            public void onSuccess(JSONObject o) {

            }
        });
    }
}
