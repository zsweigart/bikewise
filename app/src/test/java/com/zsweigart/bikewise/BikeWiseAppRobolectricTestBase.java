package com.zsweigart.bikewise;

import com.zsweigart.test.BikeWiseRobolectricTestBase;

import org.junit.ClassRule;
import org.robolectric.annotation.Config;

/**
 * Created by zsweigart on 10/23/17.
 */
@Config(manifest = "src/main/AndroidManifest.xml")
public class BikeWiseAppRobolectricTestBase extends BikeWiseRobolectricTestBase {

    @ClassRule public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();
}
