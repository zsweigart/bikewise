package com.zsweigart.bikewise.incidents.list.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsweigart.bikewise.incidents.list.list.view_holders.DefaultIncidentItemViewHolder;
import com.zsweigart.bikewise.incidents.list.list.view_holders.HazardIncidentItemViewHolder;
import com.zsweigart.bikewise.incidents.list.list.view_holders.IncidentType;
import com.zsweigart.bikewise.incidents.list.list.view_holders.TheftIncidentItemViewHolder;
import com.zsweigart.bikewise.incidents.list.list.view_holders.UnconfirmedIncidentItemViewHolder;
import com.zsweigart.models.Incident;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsweigart on 10/21/17.
 */
public class IncidentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Incident> incidentList = new ArrayList<>();
    private final IncidentListPresenter.Listener listener;

    public IncidentListAdapter(Context context, IncidentListPresenter.Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        IncidentType type = IncidentType.fromOrdinal(viewType);
        View v = inflater.inflate(type.getLayout(), parent, false);
        return IncidentType.createViewHolder(type, v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IncidentType type = IncidentType.fromName(incidentList.get(position).type());
        switch (type) {
            case HAZARD:
                ((HazardIncidentItemViewHolder) holder).setContent(incidentList.get(position), context, listener);
                break;
            case THEFT:
                ((TheftIncidentItemViewHolder) holder).setContent(incidentList.get(position), context, listener);
                break;
            case UNCONFIRMED:
                ((UnconfirmedIncidentItemViewHolder) holder).setContent(incidentList.get(position), context, listener);
                break;
            case DEFAULT:
                ((DefaultIncidentItemViewHolder) holder).setContent(incidentList.get(position), context, listener);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return incidentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return IncidentType.fromName(incidentList.get(position).type()).ordinal();
    }

    void updateIncidents(List<Incident> incidents) {
        incidentList.clear();
        incidentList.addAll(incidents);
        notifyDataSetChanged();
    }
}
