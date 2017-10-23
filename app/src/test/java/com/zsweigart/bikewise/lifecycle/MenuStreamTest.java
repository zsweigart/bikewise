package com.zsweigart.bikewise.lifecycle;

import android.view.MenuItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by zsweigart on 10/23/17.
 */
public class MenuStreamTest {

    @Mock private MenuItem menuItem;
    @Mock private MenuItem defaultMenuItem;

    private MenuStream menuStream;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        menuStream = new MenuStream();
    }

    @Test
    public void setMenuItem_putsMenuItemOnStream() {
        menuStream.setMenuItem(menuItem);

        assertThat(menuStream.menuItem().blockingFirst(defaultMenuItem)).isEqualTo(menuItem);
    }
}
