package com.zsweigart.core_components;

/**
 * Created by zsweigart on 10/18/17.
 */
public interface FragmentComponent<D extends  DependencyFragment> {

    void inject(D dependencyFragment);
}
