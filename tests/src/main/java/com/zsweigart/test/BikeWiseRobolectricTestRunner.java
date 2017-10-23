package com.zsweigart.test;


import org.junit.After;
import org.junit.Before;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

/**
 * Created by zsweigart on 10/23/17.
 */
public class BikeWiseRobolectricTestRunner extends RobolectricTestRunner {

    private static final int DEFAULT_TARGET_SDK_VERSION = 21;

    public BikeWiseRobolectricTestRunner(final Class<?> testClass) throws Exception {
        super(testClass);
    }

    /**
     * {@inheritDoc}
     *
     * <p>This is overridden to prevent test classes with no test methods an initialization error.
     *
     * @param errors The errors.
     */
    @Override
    protected void validateInstanceMethods(List<Throwable> errors) {
        validatePublicVoidNoArgMethods(After.class, false, errors);
        validatePublicVoidNoArgMethods(Before.class, false, errors);
        validateTestMethods(errors);
    }

    @Override
    protected Config buildGlobalConfig() {
        return new Config.Builder().setSdk(DEFAULT_TARGET_SDK_VERSION).build();
    }
}
