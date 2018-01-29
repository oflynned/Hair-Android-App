package com.syzible.hair.VendorInfoListing;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

public interface VendorInfoListingView extends Mvp.IView {
    Vendor getVendor();

    void setVendorDistance(double distance, String units);
}
