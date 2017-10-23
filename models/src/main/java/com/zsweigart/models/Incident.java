package com.zsweigart.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by zsweigart on 10/18/17.
 */
@AutoValue
public abstract class Incident {

    public abstract int id();

    @Nullable
    public abstract String title();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract String address();

    public abstract long occurred_at();

    public abstract long updated_at();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract Source source();

    @Nullable
    public abstract Media media();

    @Nullable
    public abstract String location_type();

    @Nullable
    public abstract String location_description();

    @Nullable
    public abstract String type();

    @Nullable
    public abstract String type_properties();

    public static TypeAdapter<Incident> typeAdapter(Gson gson) {
        return new AutoValue_Incident.GsonTypeAdapter(gson);
    }
}
