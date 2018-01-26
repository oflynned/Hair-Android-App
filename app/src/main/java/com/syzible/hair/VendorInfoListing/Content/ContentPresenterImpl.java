package com.syzible.hair.VendorInfoListing.Content;

import android.util.Log;

import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ContentPresenterImpl implements ContentPresenter {
    private ContentView contentView;
    private ContentInteractor interactor;

    @Override
    public void attach(ContentView contentView) {
        this.contentView = contentView;
        this.interactor = new ContentInteractorImpl();
    }

    @Override
    public void detach() {
        this.contentView = null;
    }

    @Override
    public void start() {
        interactor.fetch(Endpoint.GET_CONTENT_STREAM, new ContentInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {
                Log.e(getClass().getSimpleName(), statusCode + ": " + message);
            }

            @Override
            public void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException {
                List<InstaContent> content = new ArrayList<>();
                for (int i = 0; i < a.length(); i++)
                    content.add(new InstaContent(a.getJSONObject(i)));

                if (contentView != null) {
                    contentView.setAdapter(content);
                }
            }
        });
    }
}
