package com.zsweigart.bikewise.networking;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zsweigart.bikewise.MainApplication;
import com.zsweigart.bikewise.PerApp;
import com.zsweigart.models.AutoValueGsonFactory;
import com.zsweigart.networking.BikeWiseClient;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zsweigart on 10/18/17.
 */
@Module
public class NetworkModule {

    private final MainApplication mApplication;

    public NetworkModule(MainApplication app) {
        this.mApplication = app;
    }

    @Provides
    @PerApp
    GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create());
    }

    @Provides
    @PerApp
    Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BikeWiseClient.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @PerApp
    BikeWiseClient bikeWiseClient(Retrofit retrofit) {
        return new BikeWiseClient(retrofit);
    }
}
