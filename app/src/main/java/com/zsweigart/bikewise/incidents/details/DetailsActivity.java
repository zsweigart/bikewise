package com.zsweigart.bikewise.incidents.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.details.fragment.DetailsDependencyFragment;
import com.zsweigart.core_components.BaseActivity;

/**
 *
 */
public class DetailsActivity extends BaseActivity<DetailsDependencyFragment, DetailsRouter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTag() {
        return this.getClass().getCanonicalName();
    }

    @Override
    protected DetailsDependencyFragment createDepedencyFragment() {
        return new DetailsDependencyFragment();
    }

    @Override
    protected DetailsRouter createRouter(ViewGroup parentViewGroup) {
        DetailsRootView rootView = (DetailsRootView) LayoutInflater.from(this).inflate(R.layout.details_root_view,
                parentViewGroup, false);

        DetailsInteractorComponent component = DaggerDetailsInteractorComponent.builder()
                .detailsInteractorModule(new DetailsInteractorModule(this, rootView))
                .detailsFragmentComponent(dependencyFragment.getComponent())
                .build();
        return new DetailsRouter(rootView, component, new DetailsInteractor());
    }
}
