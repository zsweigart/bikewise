package com.zsweigart.bikewise;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by zsweigart on 10/18/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
