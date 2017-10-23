package com.zsweigart.bikewise.incidents.list.list.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.IncidentUtils;
import com.zsweigart.bikewise.incidents.list.list.IncidentListPresenter;
import com.zsweigart.models.Incident;

/**
 * Created by zsweigart on 10/21/17.
 */
public class HazardIncidentItemViewHolder extends RecyclerView.ViewHolder {

    private TextView titleView;
    private TextView descriptionView;

    public HazardIncidentItemViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.hazard_layout_title);
        descriptionView = itemView.findViewById(R.id.hazard_layout_description);
    }

    public void setContent(final Incident incident, Context context, final IncidentListPresenter.Listener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(incident);
            }
        });
        titleView.setText(IncidentUtils.getTitle(incident, context));
        descriptionView.setText(IncidentUtils.getDescription(incident, context));
    }
}
