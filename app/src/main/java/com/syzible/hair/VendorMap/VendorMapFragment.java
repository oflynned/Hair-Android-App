package com.syzible.hair.VendorMap;

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
import com.google.android.gms.maps.model.LatLng;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

import java.util.List;

public class VendorMapFragment extends Fragment implements MapView, OnMapReadyCallback {

    private MapPresenter mapPresenter;

    public static final LatLng ATHLONE = new LatLng(53.4232575, -7.9402598);
    public static final float INITIAL_LOCATION_ZOOM = 6.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_map, container, false);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onResume() {
        if (mapPresenter == null)
            mapPresenter = new MapPresenterImpl();

        mapPresenter.attach(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mapPresenter.detach();
        super.onPause();
    }

    @Override
    public void drawPins(List<Vendor> vendors) {

    }

    @Override
    public void setVendorSelected(Vendor vendor) {

    }

    @Override
    public void setVendorUnselected() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ATHLONE, INITIAL_LOCATION_ZOOM));
    }
}
