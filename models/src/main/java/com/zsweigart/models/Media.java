package com.zsweigart.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by zsweigart on 10/18/17.
 */
@AutoValue
public abstract class Media {

    @Nullable
    public abstract String image_url();

    @Nullable
    public abstract String image_url_thumb();

    public static TypeAdapter<Media> typeAdapter(Gson gson) {
        return new AutoValue_Media.GsonTypeAdapter(gson);
    }
}
