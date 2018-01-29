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
import android.view.Menu;
import android.view.MenuItem;

import com.syzible.hair.Common.Broadcast.Filters;
import com.syzible.hair.Common.Location.LocationService;
import com.syzible.hair.InterestedParties.InterestedPartiesFragment;
import com.syzible.hair.R;
import com.syzible.hair.VendorList.VendorListFragment;
import com.syzible.hair.VendorMap.VendorMapFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_vendors:
                    setFragment(getFragmentManager(), new VendorListFragment());
                    return true;
                case R.id.navigation_map:
                    setFragment(getFragmentManager(), new VendorMapFragment());
                    return true;
                case R.id.navigation_interested_parties:
                    setFragment(getFragmentManager(), new InterestedPartiesFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragment(getFragmentManager(), new VendorListFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
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
}
