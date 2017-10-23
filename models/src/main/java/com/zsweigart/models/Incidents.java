package com.zsweigart.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by zsweigart on 10/21/17.
 */
@AutoValue
public abstract class Incidents {

    public abstract List<Incident> incidents();

    public static TypeAdapter<Incidents> typeAdapter(Gson gson) {
        return new AutoValue_Incidents.GsonTypeAdapter(gson);
    }
}
