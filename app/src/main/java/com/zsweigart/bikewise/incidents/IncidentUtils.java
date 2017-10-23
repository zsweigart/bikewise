package com.zsweigart.bikewise.incidents;

import android.content.Context;
import android.text.TextUtils;

import com.zsweigart.bikewise.R;
import com.zsweigart.models.Incident;

/**
 * Created by zsweigart on 10/21/17.
 */
public class IncidentUtils {

    private IncidentUtils() { }

    public static String getTitle(Incident incident, Context context) {
        String defaultTitle = context.getString(R.string.no_title);
        return getStringOrDefault(defaultTitle, incident.title());
    }

    public static String getDescription(Incident incident, Context context) {
        String defaultDescription = context.getString(R.string.no_description);
        return getStringOrDefault(defaultDescription, incident.description());
    }

    private static String getStringOrDefault(String defaultString, String nullableString) {
        return TextUtils.isEmpty(nullableString) ? defaultString : nullableString;
    }
}
