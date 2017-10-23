package com.zsweigart.core_components;

/**
 * Created by zsweigart on 10/19/17.
 */
public abstract class Builder <R extends Router, D> {

    private final D dependency;

    public Builder(D dependency) {
        this.dependency = dependency;
    }

    protected D getDependency() {
        return dependency;
    }
}
