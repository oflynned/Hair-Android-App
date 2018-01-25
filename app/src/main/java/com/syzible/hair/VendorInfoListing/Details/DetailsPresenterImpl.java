package com.syzible.hair.VendorInfoListing.Details;

import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

public class DetailsPresenterImpl implements DetailsPresenter {
    private DetailsView detailsView;
    private Vendor vendor;

    @Override
    public void attach(DetailsView detailsView) {
        this.detailsView = detailsView;
        this.vendor = detailsView.getVendor();
    }

    @Override
    public void detach() {
        this.detailsView = null;
    }

    @Override
    public void start() {
        if (detailsView != null) {
            try {
                detailsView.setOpeningHours(vendor.getOpeningHours());
                detailsView.setNowOpen(vendor);
            } catch (OpeningTimeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
