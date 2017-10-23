package com.zsweigart.bikewise;

import org.junit.ClassRule;

/**
 * Created by zsweigart on 10/23/17.
 */
public class BikeWiseTestBase {

    @ClassRule public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();
}
