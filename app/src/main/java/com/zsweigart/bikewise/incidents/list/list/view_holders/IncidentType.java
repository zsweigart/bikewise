package com.zsweigart.bikewise.incidents.list.list.view_holders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zsweigart.bikewise.R;

/**
 * Created by zsweigart on 10/21/17.
 */
public enum IncidentType {
    HAZARD("Hazard", R.layout.hazard_incident_layout),
    THEFT("Theft", R.layout.theft_incident_layout),
    UNCONFIRMED("Unconfirmed", R.layout.unconfirmed_incident_layout),
    DEFAULT("Default", R.layout.default_incident_layout);

    private final String name;
    @LayoutRes private final int layout;

    IncidentType(String name, @LayoutRes int layout) {
        this.name = name;
        this.layout = layout;
    }

    public static IncidentType fromName(String name) {
        for (IncidentType incidentType : IncidentType.values()) {
            if (incidentType.name.equalsIgnoreCase(name)) {
                return incidentType;
            }
        }

        return DEFAULT;
    }

    public static IncidentType fromOrdinal(int ordinal) {
        for (IncidentType incidentType : IncidentType.values()) {
            if (incidentType.ordinal() == ordinal) {
                return incidentType;
            }
        }

        return DEFAULT;
    }

    public int getLayout() {
        return layout;
    }

    public static RecyclerView.ViewHolder createViewHolder(IncidentType incidentType, View v) {
        switch (incidentType) {
            case HAZARD:
                return new HazardIncidentItemViewHolder(v);
            case THEFT:
                return new TheftIncidentItemViewHolder(v);
            case UNCONFIRMED:
                return new UnconfirmedIncidentItemViewHolder(v);
            default:
                return new DefaultIncidentItemViewHolder(v);
        }
    }
}
