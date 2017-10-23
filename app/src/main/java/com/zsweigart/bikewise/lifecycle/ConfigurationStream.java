package com.zsweigart.bikewise.lifecycle;

import android.app.Application;
import android.content.res.Configuration;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/20/17.
 */
public class ConfigurationStream {

    private BehaviorRelay<Configuration> configurationRelay;

    public ConfigurationStream(Application application) {
        configurationRelay = BehaviorRelay.createDefault(application.getResources().getConfiguration());
    }

    public Observable<Configuration> configuration() {
        return configurationRelay.hide();
    }

    public void setConfiguration(Configuration configuration) {
        configurationRelay.accept(configuration);
    }
}
