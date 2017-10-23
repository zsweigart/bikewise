package com.zsweigart.bikewise;

import android.app.Application;

import com.zsweigart.bikewise.networking.NetworkModule;
import com.zsweigart.bikewise.storage.StorageModule;

/**
 * Created by zsweigart on 9/23/17.
 */
public class MainApplication extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent
                .builder()
                .mainModule(new MainModule(this))
                .networkModule(new NetworkModule( this))
                .storageModule(new StorageModule(this))
                .build();

        mainComponent.inject(this);
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
