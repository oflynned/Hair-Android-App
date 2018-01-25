package com.syzible.hair.VendorInfoListing.Content;

public class ContentPresenterImpl implements ContentPresenter {
    private ContentView contentView;

    @Override
    public void attach(ContentView contentView) {
        this.contentView = contentView;
    }

    @Override
    public void detach() {
        this.contentView = null;
    }
}
