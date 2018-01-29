package com.syzible.hair.VendorInfoListing.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

public class MapSectionVendorInfoFragment extends Fragment implements MapSectionVendorInfoView, OnMapReadyCallback{
    private MapSectionVendorInfoPresenter presenter;
    private Vendor vendor;

    private static final float CITYWIDE_ZOOM = 13.5f;
    private static final float VENDOR_IN_FOCUS_ZOOM = 15.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vendor_map, container, false);
    }

    @Override
    public void onResume() {
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (presenter == null)
            presenter = new MapSectionVendorInfoPresenterImpl();

        presenter.attach(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.detach();
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        googleMap.addMarker(
                new MarkerOptions()
                        .position(vendor.getCoords())
                        .title(vendor.getVendorName()));

        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(vendor.getCoords(), CITYWIDE_ZOOM));
        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(vendor.getCoords(), VENDOR_IN_FOCUS_ZOOM)
        );
    }

    @Override
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
