package com.syzible.hair.Common;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.syzible.hair.Common.Broadcast.Filters;
import com.syzible.hair.Common.Location.LocationService;
import com.syzible.hair.Common.Persistence.Prefs;
import com.syzible.hair.InterestedParties.InterestedPartiesFragment;
import com.syzible.hair.R;
import com.syzible.hair.VendorList.VendorListFragment;
import com.syzible.hair.VendorMap.VendorMapFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private VendorListFragment vendorListFragment;
    private VendorMapFragment vendorMapFragment;
    private InterestedPartiesFragment interestedPartiesFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_vendors:
                    setFragment(getFragmentManager(), vendorListFragment);
                    return true;
                case R.id.navigation_map:
                    setFragment(getFragmentManager(), vendorMapFragment);
                    return true;
                case R.id.navigation_interested_parties:
                    setFragment(getFragmentManager(), interestedPartiesFragment);
                    return true;
            }
            return false;
        }
    };

    private BroadcastReceiver onLocationChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), Filters.location_update.toString())) {
                double lat = Double.parseDouble(intent.getStringExtra("lat"));
                double lng = Double.parseDouble(intent.getStringExtra("lng"));

                Prefs.setDoublePref(getApplicationContext(), Prefs.Pref.last_lat, lat);
                Prefs.setDoublePref(getApplicationContext(), Prefs.Pref.last_lng, lng);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        vendorListFragment = new VendorListFragment();
        vendorMapFragment = new VendorMapFragment();
        interestedPartiesFragment = new InterestedPartiesFragment();

        setFragment(getFragmentManager(), vendorListFragment);
    }

    @Override
    protected void onResume() {
        checkPermission();
        registerReceiver(onLocationChange,
                new IntentFilter(Filters.location_update.toString()));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(onLocationChange);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() < 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Close " + this.getString(R.string.app_name) + "?")
                    .setMessage("Click okay to close the app.")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    public static void setFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            clearBackstack(fragmentManager);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void setFragmentBackstack(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void removeTopFragment(FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void clearBackstack(FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.executePendingTransactions();
        }
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 123);
        } else {
            this.startService(new Intent(this, LocationService.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.startService(new Intent(this, LocationService.class));
        }
    }
}
