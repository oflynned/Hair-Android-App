package com.syzible.hair.VendorInfoListing.Details;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

public interface DetailsPresenter extends Mvp.IPresenter<DetailsView> {
    void attach(DetailsView detailsView);

    void start();
}
