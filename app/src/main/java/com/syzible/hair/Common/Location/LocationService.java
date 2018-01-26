package com.syzible.hair.Common.Location;

import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.syzible.hair.Common.Broadcast.Filters;
import com.yayandroid.locationmanager.base.LocationBaseService;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;

public class LocationService extends LocationBaseService {
    private boolean isLocationRequested = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return LocationConfig.silentConfiguration();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if (!isLocationRequested) {
            isLocationRequested = true;
            getLocation();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

    @Override
    public void onLocationChanged(Location location) {
        Intent intent = new Intent(Filters.location_update.toString());
        intent.putExtra("lat", String.valueOf(location.getLatitude()));
        intent.putExtra("lng", String.valueOf(location.getLongitude()));
        sendBroadcast(intent);
    }

    @Override
    public void onLocationFailed(@FailType int type) {
        stopSelf();
    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {

    }
}
