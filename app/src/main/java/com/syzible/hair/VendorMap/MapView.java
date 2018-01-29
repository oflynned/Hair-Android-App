package com.syzible.hair.VendorMap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

import java.util.List;

public interface MapView extends Mvp.IView {
    void drawPins(List<Vendor> vendors);

    void setVendorSelected(Vendor vendor);

    void updateCameraProjection(LatLng location);
}
