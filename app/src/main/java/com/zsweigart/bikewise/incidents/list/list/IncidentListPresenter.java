package com.zsweigart.bikewise.incidents.list.list;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zsweigart.core_components.Presenter;
import com.zsweigart.models.Incident;

import java.util.List;

/**
 * Created by zsweigart on 10/19/17.
 */
public class IncidentListPresenter extends Presenter<IncidentListView> {

    private final Listener listener;
    private final IncidentListAdapter incidentListAdapter;

    public IncidentListPresenter(IncidentListView view, Listener listener) {
        super(view);
        this.listener = listener;
        incidentListAdapter = new IncidentListAdapter(view.getContext(), listener);
    }

    @Override
    protected void didLoad() {
        super.didLoad();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getView().getContext(),
                DividerItemDecoration.VERTICAL);

        view.setLayoutManager(layoutManager);
        view.setAdapter(incidentListAdapter);
        view.addItemDecoration(dividerItemDecoration);
    }

    public void updateIncidentList(List<Incident> incidentList) {
        incidentListAdapter.updateIncidents(incidentList);
    }

    /**
     *
     */
    public interface Listener {
        void onItemClick(Incident incident);
    }
}
