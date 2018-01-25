package com.syzible.hair.VendorMap.VendorMapView;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

public interface MapView extends Mvp.IView {
    void loadMap();

    void drawPins();

    void setVendorSelected(Vendor vendor);

    void setVendorUnselected();
}
