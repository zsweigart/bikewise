package com.zsweigart.core_components;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Retains DI graph on rotation
 */
public abstract class DependencyFragment<C extends FragmentComponent> extends Fragment {

    protected C component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        component = createComponent();
        component.inject(this);
        ((BaseActivity) getActivity()).onFragmentReady();
    }

    protected abstract C createComponent();

    public C getComponent() {
        return component;
    }
}
