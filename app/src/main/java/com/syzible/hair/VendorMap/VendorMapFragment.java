package com.syzible.hair.VendorMap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syzible.hair.Common.Location.LocationService;
import com.syzible.hair.Common.MainActivity;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;
import com.syzible.hair.VendorInfoListing.VendorInfoListingFragment;

import java.util.List;

public class VendorMapFragment extends Fragment implements MapView, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;
    private MapPresenter mapPresenter;
    private List<Vendor> vendors;

    private static final LatLng DUBLIN = new LatLng(53.347239, -6.259098);
    private static final float CITYWIDE_ZOOM = 13.5f;
    private static final float MY_LOCATION_ZOOM = 15.0f;

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
        mapPresenter.getPins();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapPresenter.detach();
        super.onPause();
    }

    @Override
    public void drawPins(List<Vendor> vendors) {
        this.vendors = vendors;
        googleMap.clear();

        for (Vendor vendor : vendors) {
            googleMap.addMarker(new MarkerOptions()
                    .title(vendor.getVendorName())
                    .snippet(vendor.getAddress())
                    .position(vendor.getCoords()));
        }
    }

    @Override
    public void setVendorSelected(Vendor vendor) {
        VendorInfoListingFragment fragment = new VendorInfoListingFragment();
        fragment.setVendor(vendor);
        MainActivity.setFragmentBackstack(getFragmentManager(), fragment);
    }

    @Override
    public void updateCameraProjection(LatLng location) {
        CameraUpdate projection = CameraUpdateFactory.newLatLngZoom(location, MY_LOCATION_ZOOM);
        googleMap.animateCamera(projection);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DUBLIN, CITYWIDE_ZOOM));
        this.googleMap = googleMap;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        for (Vendor vendor : vendors) {
            if (vendor.getVendorName().equals(marker.getTitle())) {
                VendorInfoListingFragment fragment = new VendorInfoListingFragment();
                fragment.setVendor(vendor);

                MainActivity.setFragmentBackstack(getFragmentManager(), fragment);
            }
        }
    }
}
