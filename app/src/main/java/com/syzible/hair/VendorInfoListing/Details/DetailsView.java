package com.syzible.hair.VendorInfoListing.Details;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.OpeningHours;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

public interface DetailsView extends Mvp.IView {

    void setOpeningHours(OpeningHours openingHours);

    void setNowOpen(Vendor vendor) throws OpeningTimeNotFoundException;

    Vendor getVendor();
}
