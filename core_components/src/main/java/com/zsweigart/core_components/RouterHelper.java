package com.zsweigart.core_components;

import static org.mockito.Mockito.verify;

/**
 * Created by zsweigart on 10/23/17.
 */
public class RouterHelper {

    private RouterHelper() { }

    public static void verifyAttached(Router router) {
        verify(router).dispatchAttach();
    }

    public static void verifyDetached(Router router) {
        verify(router).dispatchDetach();
    }
}
