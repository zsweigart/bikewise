package com.zsweigart.bikewise.incidents.details;

import android.content.Context;
import android.text.TextUtils;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.IncidentUtils;
import com.zsweigart.core_components.Presenter;
import com.zsweigart.models.Incident;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zsweigart on 10/19/17.
 */
public class DetailsPresenter extends Presenter<DetailsRootView> {

    public DetailsPresenter(DetailsRootView view) {
        super(view);
    }

    void updateFields(Incident incident) {
        Context context = getView().getContext();

        setTitle(IncidentUtils.getTitle(incident, context));
        view.setOccurredAtText(context.getString(R.string.occurred_at, getTime(incident.occurred_at())));
        view.setAddressText(incident.address());
        setDescription(IncidentUtils.getDescription(incident, context));
    }

    private void setTitle(String title) {
        getView().setTitle(title);
    }

    private void setDescription(String description) {
        getView().setDescription(description);
    }

    private String getTime(long time) {
        return DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()).format(new Date(time));
    }
}
