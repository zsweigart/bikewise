package com.zsweigart.bikewise.incidents;

import com.zsweigart.bikewise.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zsweigart on 10/22/17.
 */
@Module
public class IncidentModule {
    @Provides
    @PerApp
    SelectedIncidentStream selectedIncidentStream() {
        return new SelectedIncidentStream();
    }
}
