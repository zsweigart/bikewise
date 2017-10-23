package com.zsweigart.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.common.base.Optional;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsweigart.models.AutoValueGsonFactory;
import com.zsweigart.models.Incident;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zsweigart on 10/21/17.
 */
public class SharedPreferencesIncidentStorage implements IncidentStorage {

    private static final String SHARED_PREFS_NAME = "incident_prefs";
    private static final String INCIDENTS_KEY = "incidents_key";

    private final Type incidentListType = new TypeToken<List<Incident>>() { }.getType();

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreferencesIncidentStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        this.gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create();
    }

    @Override
    public void putIncidentList(List<Incident> incidentList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INCIDENTS_KEY, gson.toJson(incidentList));
        editor.apply();
    }

    @Override
    public Observable<Optional<List<Incident>>> getIncidentList() {
        String incidentListString = sharedPreferences.getString(INCIDENTS_KEY, "");
        if (TextUtils.isEmpty(incidentListString)) {
            return Observable.just(Optional.<List<Incident>>absent());
        }

        List<Incident> incidents = gson.fromJson(incidentListString, incidentListType);
        return Observable.just(Optional.of(incidents));
    }
}
