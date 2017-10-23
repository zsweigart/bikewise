package com.zsweigart.core_components;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public abstract class BaseActivity<D extends DependencyFragment, R extends Router> extends AppCompatActivity {

    protected D dependencyFragment;
    protected R router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // find the retained fragment on activity restarts
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyFragment = (D) fragmentManager.findFragmentByTag(getTag());

        // create the fragment and data the first time
        if (dependencyFragment == null) {
            // add the fragment
            dependencyFragment = createDepedencyFragment();
            fragmentManager.beginTransaction().add(dependencyFragment, getTag()).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (router != null) {
            router.dispatchDetach();
        }

        router = null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    void onFragmentReady() {
        ViewGroup rootViewGroup = ((ViewGroup) findViewById(android.R.id.content));
        router = createRouter(rootViewGroup);

        if (router instanceof ViewRouter) {
            ViewRouter viewRouter = ((ViewRouter) router);
            View view = viewRouter.getView();
            if (view != null) {
                rootViewGroup.addView(view);
            }
        }

        router.dispatchAttach();
    }

    protected D getDependencyFragment() {
        return dependencyFragment;
    }

    protected abstract String getTag();

    protected abstract D createDepedencyFragment();

    protected abstract R createRouter(ViewGroup parentViewGroup);
}
