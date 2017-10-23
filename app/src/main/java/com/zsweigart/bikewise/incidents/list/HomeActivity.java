package com.zsweigart.bikewise.incidents.list;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.zsweigart.bikewise.R;
import com.zsweigart.bikewise.incidents.list.actionbar.ActionBarBuilder;
import com.zsweigart.bikewise.incidents.list.fragment.HomeDependencyFragment;
import com.zsweigart.bikewise.incidents.list.list.IncidentListBuilder;
import com.zsweigart.bikewise.incidents.list.searchbar.SearchBarBuilder;
import com.zsweigart.core_components.BaseActivity;

/**
 * Main launcher activity
 */
public class HomeActivity extends BaseActivity<HomeDependencyFragment, HomeRouter> {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dependencyFragment.getConfigurationStream().setConfiguration(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        dependencyFragment.getMenuStream().setMenuItem(item);
        return true;
    }

    @Override
    protected String getTag() {
        return this.getClass().getCanonicalName();
    }

    @Override
    protected HomeDependencyFragment createDepedencyFragment() {
        return new HomeDependencyFragment();
    }

    @Override
    protected HomeRouter createRouter(ViewGroup parentViewGroup) {
        HomeRootView rootView = (HomeRootView) LayoutInflater.from(getBaseContext()).inflate(R.layout.home_root_view,
                parentViewGroup, false);
        HomeInteractor homeInteractor = new HomeInteractor();

        HomeComponent component = DaggerHomeComponent.builder()
                .homeModule(new HomeModule(rootView, this, homeInteractor))
                .homeFragmentComponent(dependencyFragment.getComponent())
                .build();
        return new HomeRouter(rootView, component, homeInteractor, new ActionBarBuilder(component),
                new IncidentListBuilder(component), new SearchBarBuilder(component));
    }
}
