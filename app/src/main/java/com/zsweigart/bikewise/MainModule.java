package com.zsweigart.bikewise;

import android.content.Context;

import com.zsweigart.bikewise.lifecycle.ConfigurationStream;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 9/24/17.
 */
@Module
public class MainModule {
    private final MainApplication mApplication;

    public MainModule(MainApplication app) {
        mApplication = app;
    }

    @Provides
    @PerApp
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @PerApp
    MainApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @PerApp
    ConfigurationStream configurationStream() {
        return new ConfigurationStream(mApplication);
    }
}
