package com.zsweigart.bikewise.storage;

import com.google.gson.Gson;
import com.zsweigart.bikewise.MainApplication;
import com.zsweigart.bikewise.PerApp;
import com.zsweigart.storage.IncidentStorage;
import com.zsweigart.storage.SharedPreferencesIncidentStorage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 10/21/17.
 */
@Module
public class StorageModule {

    private final MainApplication mApplication;

    public StorageModule(MainApplication app) {
        this.mApplication = app;
    }

    @Provides
    @PerApp
    IncidentStorage incidentStorage() {
        return new SharedPreferencesIncidentStorage(mApplication);
    }
}
