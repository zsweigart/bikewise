package com.zsweigart.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by zsweigart on 10/18/17.
 */
@AutoValue
public abstract class Source {

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String html_url();

    @Nullable
    public abstract String api_url();

    public static TypeAdapter<Source> typeAdapter(Gson gson) {
        return new AutoValue_Source.GsonTypeAdapter(gson);
    }
}
